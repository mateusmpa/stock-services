-- Tabelas

create table es_movimentacao_tipos(
    id number not null,
    nom_nome varchar2(100) not null,
    nom_tipo varchar2(1) not null,
    primary key (id),
    constraint tipo_ck check (nom_tipo in('e', 's'))
);

create table es_depositos(
    id number generated always as identity,
    nom_nome varchar2(100) not null,
    nom_codigo varchar2(100) not null unique,
    primary key (id)
);

create table es_produtos(
    id number generated always as identity,
    nom_nome varchar2(100) not null,
    nom_codigo varchar2(100) not null unique,
    num_quantidade_minima number not null,
    dec_valor_medio decimal(10,2) default 0 not null,
    primary key (id)
);

create table es_fornecedores(
    id number generated always as identity,
    nom_nome varchar2(100) not null,
    nom_codigo varchar2(100) not null unique,
    primary key (id)
);

create table es_depositos_produtos(
    id number generated always as identity,
    id_deposito number not null,
    id_produto number not null,
    num_quantidade_total number not null,
    num_quantidade_minima number not null,
    primary key (id),
    constraint depoprod_depo_fk foreign key (id_deposito) references es_depositos(id),
    constraint depoprod_prod_fk foreign key (id_produto) references es_produtos(id),
    constraint deposito_produto_uk unique (id_deposito, id_produto),
    constraint num_quantidade_total_ck check (num_quantidade_total >= 0)
);

create table es_produtos_fornecedores(
    id number generated always as identity,
    id_produto number not null,
    id_fornecedor number not null,
    primary key (id),
    constraint prodforn_prod_fk foreign key (id_produto) references es_produtos(id),
    constraint prodforn_forn_fk foreign key (id_fornecedor) references es_fornecedores(id),
    constraint produto_fornecedor_uk unique (id_produto, id_fornecedor)
);

create table es_movimentacoes(
    id number generated always as identity,
    id_movimentacao_tipo number not null,
    id_deposito_produto number not null,
    num_quantidade number not null,
    dec_valor_unitario decimal(10,2),
    dat_registro timestamp,
    primary key (id),
    constraint movi_movitipo_fk foreign key (id_movimentacao_tipo) references es_movimentacao_tipos(id),
    constraint movi_depoprod_fk foreign key (id_deposito_produto) references es_depositos_produtos(id)
);

create index depoprod_depo_fk_i on es_depositos_produtos (id_deposito);
create index depoprod_prod_fk_i on es_depositos_produtos (id_produto);
create index prodforn_prod_fk_i on es_produtos_fornecedores (id_produto);
create index prodforn_forn_fk_i on es_produtos_fornecedores (id_fornecedor);
create index movi_movitipo_fk_i on es_movimentacoes (id_movimentacao_tipo);
create index movi_depoprod_fk_i on es_movimentacoes (id_deposito_produto);

-- Views

create view vw_listar_movimentacoes as
select
    es_movimentacao_tipos.id as num_codigo,
    es_movimentacao_tipos.nom_nome as nom_movimentacao,
    es_depositos.nom_codigo as nom_codigo_deposito,
    es_depositos.nom_nome as nom_deposito,
    es_produtos.nom_codigo as nom_codigo_produto,
    es_produtos.nom_nome as nom_produto,
    es_movimentacoes.num_quantidade as num_quantidade,
    es_movimentacoes.dec_valor_unitario as dec_valor_unitario,
    (es_movimentacoes.num_quantidade * es_movimentacoes.dec_valor_unitario) as dec_valor_total,
    to_char(es_movimentacoes.dat_registro, 'yyyy-mm-dd hh24:mi:ss') as dat_registro
from
    es_movimentacoes,
    es_movimentacao_tipos,
    es_depositos_produtos,
    es_depositos,
    es_produtos
where
    es_movimentacao_tipos.id = es_movimentacoes.id_movimentacao_tipo
    and es_depositos.id = es_depositos_produtos.id_deposito
    and es_produtos.id = es_depositos_produtos.id_produto
    and es_depositos_produtos.id = es_movimentacoes.id_deposito_produto
order by
    es_movimentacoes.dat_registro;

create view vw_listar_medias as
select
    nom_codigo_produto,
    nom_produto,
    sum(dec_valor_total) as dec_valor_total,
    sum(num_quantidade) as num_quantidade,
    round((sum(dec_valor_total) / sum(num_quantidade)), 2) as dec_media
from vw_listar_movimentacoes
where num_codigo = 1
group by nom_codigo_produto, nom_produto;

create view vw_listar_depositos_produtos as
select
    es_depositos.nom_codigo as nom_codigo_desposito,
    es_depositos.nom_nome as nom_desposito,
    es_produtos.nom_codigo as nom_codigo_produto,
    es_produtos.nom_nome as nom_produto,
    es_depositos_produtos.num_quantidade_total as num_quantidade,
    es_depositos_produtos.num_quantidade_minima as num_quantidade_minima
from
    es_depositos_produtos,
    es_produtos,
    es_depositos
where
    es_depositos.id = es_depositos_produtos.id_deposito
    and es_produtos.id = es_depositos_produtos.id_produto
order by
    es_depositos.nom_codigo;

create view vw_listar_produtos_fornecedores as
select
    es_produtos.nom_nome as nom_produto,
    es_produtos.nom_codigo as nom_codigo_produto,
    es_fornecedores.nom_nome as nom_fornecedor,
    es_fornecedores.nom_codigo as nom_codigo_fornecedor
from
    es_produtos_fornecedores,
    es_fornecedores,
    es_produtos
where
    es_produtos.id = es_produtos_fornecedores.id_produto
    and es_fornecedores.id = es_produtos_fornecedores.id_fornecedor
order by
    es_produtos.nom_nome;

create view vw_listar_produtos as
select
    es_produtos.id as id,
    es_produtos.nom_nome as nom_nome,
    es_produtos.nom_codigo as nom_codigo,
    es_produtos.num_quantidade_minima as num_quantidade_minima,
    es_produtos.dec_valor_medio as dec_valor_medio,
    nvl(sum(vw_listar_depositos_produtos.num_quantidade), 0) as num_quantidade
from
    es_produtos
left join
    vw_listar_depositos_produtos
on
    vw_listar_depositos_produtos.nom_codigo_produto = es_produtos.nom_codigo
group by
    es_produtos.id,
    es_produtos.nom_nome,
    es_produtos.nom_codigo,
    es_produtos.num_quantidade_minima,
    es_produtos.dec_valor_medio
order by
    es_produtos.id;

-- Procedures

create or replace procedure prc_adicionar_produto_deposito(
    p_codigo_deposito in varchar2, p_codigo_produto in varchar2, p_quantidade in number, p_quantidade_minima in number, p_movimentacao in number, p_valor_unitario in number default null
) is
begin
    insert into es_depositos_produtos (id_deposito, id_produto, num_quantidade_total, num_quantidade_minima)
    values (
        (select id from es_depositos where nom_codigo = p_codigo_deposito),
        (select id from es_produtos where nom_codigo = p_codigo_produto),
        p_quantidade,
        p_quantidade_minima
    );

    insert into es_movimentacoes (id_movimentacao_tipo, id_deposito_produto, num_quantidade, dec_valor_unitario, dat_registro)
    values (
        p_movimentacao,
        (
            select id from es_depositos_produtos
            where
                id_deposito = (select id from es_depositos where nom_codigo = p_codigo_deposito)
                and id_produto = (select id from es_produtos where nom_codigo = p_codigo_produto)
        ),
        p_quantidade,
        p_valor_unitario,
        systimestamp
    );

    if p_movimentacao = 1 then
        update es_produtos set dec_valor_medio = (select dec_media from vw_listar_medias where nom_codigo_produto = p_codigo_produto)
        where nom_codigo = p_codigo_produto;
    end if;
    commit;
end;
/

create or replace procedure prc_atualizar_quantidade_produto_deposito(
    p_codigo_deposito in varchar2, p_codigo_produto in varchar2, p_quantidade in number, p_movimentacao in number, p_valor_unitario in number default null
) is
    v_tipo varchar2(1);
begin
    select nom_tipo into v_tipo from es_movimentacao_tipos where id = p_movimentacao;

    if v_tipo = 'e' then
        update es_depositos_produtos set num_quantidade_total = (num_quantidade_total + p_quantidade)
        where
            id_deposito = (select id from es_depositos where nom_codigo = p_codigo_deposito)
            and id_produto = (select id from es_produtos where nom_codigo = p_codigo_produto);
    else
        update es_depositos_produtos set num_quantidade_total = (num_quantidade_total - p_quantidade)
        where
            id_deposito = (select id from es_depositos where nom_codigo = p_codigo_deposito)
            and id_produto = (select id from es_produtos where nom_codigo = p_codigo_produto);
    end if;

    insert into es_movimentacoes (id_movimentacao_tipo, id_deposito_produto, num_quantidade, dec_valor_unitario, dat_registro)
    values (
        p_movimentacao,
        (
            select id from es_depositos_produtos
            where
                id_deposito = (select id from es_depositos where nom_codigo = p_codigo_deposito)
                and id_produto = (select id from es_produtos where nom_codigo = p_codigo_produto)
        ),
        p_quantidade,
        p_valor_unitario,
        systimestamp
    );

    if p_movimentacao = 1 then
        update es_produtos set dec_valor_medio = (select dec_media from vw_listar_medias where nom_codigo_produto = p_codigo_produto)
        where nom_codigo = p_codigo_produto;
    end if;
    commit;
end;
/

create or replace procedure prc_registrar_produto_fornecedor(
    p_codigo_produto in varchar2, p_codigo_fornecedor in varchar2
) is
begin
    insert into es_produtos_fornecedores (id_produto, id_fornecedor)
    values (
        (select id from es_produtos where nom_codigo = p_codigo_produto),
        (select id from es_fornecedores where nom_codigo = p_codigo_fornecedor)
    );
    commit;
end;
/

-- Dados

insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (1, 'Entrada por Nota Fiscal', 'e');
insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (2, 'Entrada por Doação', 'e');
insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (3, 'Saída por Nota Fiscal', 's');
insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (4, 'Saída por Doação', 's');
insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (5, 'Transferência entre depósitos (entrada)', 'e');
insert into es_movimentacao_tipos (id, nom_nome, nom_tipo) values (6, 'Transferência entre depósitos (saída)', 's');

insert into es_depositos (nom_nome, nom_codigo) values ('Salvador', 'salv_0001');
insert into es_depositos (nom_nome, nom_codigo) values ('São Paulo', 'saop_0001');
insert into es_depositos (nom_nome, nom_codigo) values ('Rio Grande do Sul', 'riog_0001');

insert into es_produtos (nom_nome, nom_codigo, num_quantidade_minima) values ('Smartphone', 'smar_0001', 3000);
insert into es_produtos (nom_nome, nom_codigo, num_quantidade_minima) values ('Tênis esportivo', 'teni_0001', 500);
insert into es_produtos (nom_nome, nom_codigo, num_quantidade_minima) values ('Relógio de pulso', 'relo_0001', 800);

insert into es_fornecedores (nom_nome, nom_codigo) values ('Acme Corporation', 'acme_0001');
insert into es_fornecedores (nom_nome, nom_codigo) values ('Global Solutions', 'glob_0001');
insert into es_fornecedores (nom_nome, nom_codigo) values ('Prime Industries', 'prim_0001');

exec prc_registrar_produto_fornecedor('smar_0001', 'acme_0001');
exec prc_registrar_produto_fornecedor('smar_0001', 'glob_0001');
exec prc_registrar_produto_fornecedor('teni_0001', 'glob_0001');

exec prc_adicionar_produto_deposito('salv_0001', 'smar_0001', 800, 100, 1, 1050);
exec prc_atualizar_quantidade_produto_deposito('salv_0001', 'smar_0001', 50, 3);
exec prc_atualizar_quantidade_produto_deposito('salv_0001', 'smar_0001', 100, 2);
exec prc_atualizar_quantidade_produto_deposito('salv_0001', 'smar_0001', 55, 1, 2000.5);
exec prc_atualizar_quantidade_produto_deposito('salv_0001', 'smar_0001', 110, 6);
exec prc_adicionar_produto_deposito('saop_0001', 'smar_0001', 110, 80, 5);
exec prc_adicionar_produto_deposito('saop_0001', 'teni_0001', 433, 300, 1, 890.9);

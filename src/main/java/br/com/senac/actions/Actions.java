package br.com.senac.actions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.senac.entities.Depositos;
import br.com.senac.entities.DepositosProdutos;
import br.com.senac.entities.Fornecedores;
import br.com.senac.entities.Movimentacoes;
import br.com.senac.entities.Produtos;
import br.com.senac.entities.ProdutosFornecedores;

public class Actions {
    public Depositos getDeposito(String codigo, String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Depositos deposito = new Depositos();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select id, nom_nome, nom_codigo from es_depositos where nom_codigo = '" + codigo + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                deposito.setId(rs.getString(1));
                deposito.setNome(rs.getString(2));
                deposito.setCodigo(rs.getString(3));
            }

            rs.close();
            ps.close();
            conn.close();

            return deposito;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            deposito.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            deposito.setMensagem("Erro");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return deposito;
    }

    public Fornecedores getFornecedor(String codigo, String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Fornecedores fornecedor = new Fornecedores();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select id, nom_nome, nom_codigo from es_fornecedores where nom_codigo = '" + codigo + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                fornecedor.setId(rs.getString(1));
                fornecedor.setNome(rs.getString(2));
                fornecedor.setCodigo(rs.getString(3));
            }

            rs.close();
            ps.close();
            conn.close();

            return fornecedor;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            fornecedor.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            fornecedor.setMensagem("Erro");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return fornecedor;
    }

    public Produtos getProduto(String codigo, String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Produtos produto = new Produtos();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select id, nom_nome, nom_codigo, num_quantidade_minima, dec_valor_medio, num_quantidade " +
                            "from vw_listar_produtos " +
                            "where nom_codigo = '" + codigo + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                produto.setId(rs.getString(1));
                produto.setNome(rs.getString(2));
                produto.setCodigo(rs.getString(3));
                produto.setQuantidadeMinima(rs.getString(4));
                produto.setValorMedio(rs.getString(5));
                produto.setQuantidade(rs.getString(6));
            }

            rs.close();
            ps.close();
            conn.close();

            return produto;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            produto.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            produto.setMensagem("Erro");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return produto;
    }

    public List<Depositos> getDepositos(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Depositos> depositos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement("select id, nom_nome, nom_codigo from es_depositos");
            rs = ps.executeQuery();

            while (rs.next()) {
                Depositos deposito = new Depositos();

                deposito.setId(rs.getString(1));
                deposito.setNome(rs.getString(2));
                deposito.setCodigo(rs.getString(3));

                depositos.add(deposito);
            }

            rs.close();
            ps.close();
            conn.close();

            return depositos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Depositos deposito = new Depositos();
            deposito.setMensagem("Erro");
            depositos.add(deposito);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Depositos deposito = new Depositos();
            deposito.setMensagem("Erro");
            depositos.add(deposito);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositos;
    }

    public List<DepositosProdutos> getDepositosProdutos(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_codigo_desposito, nom_desposito, nom_codigo_produto, nom_produto, num_quantidade, num_quantidade_minima "
                            +
                            "from vw_listar_depositos_produtos");
            rs = ps.executeQuery();

            while (rs.next()) {
                DepositosProdutos depositoProduto = new DepositosProdutos();

                depositoProduto.setCodigoDeposito(rs.getString(1));
                depositoProduto.setDeposito(rs.getString(2));
                depositoProduto.setCodigoProduto(rs.getString(3));
                depositoProduto.setProduto(rs.getString(4));
                depositoProduto.setQuantidade(rs.getString(5));
                depositoProduto.setQuantidadeMinima(rs.getString(6));

                depositosProdutos.add(depositoProduto);
            }

            rs.close();
            ps.close();
            conn.close();

            return depositosProdutos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositosProdutos;
    }

    public List<DepositosProdutos> getDepositosProdutosParaRepor(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_codigo_desposito, nom_desposito, nom_codigo_produto, nom_produto, num_quantidade, num_quantidade_minima "
                            +
                            "from vw_listar_depositos_produtos " +
                            "where num_quantidade < num_quantidade_minima");
            rs = ps.executeQuery();

            while (rs.next()) {
                DepositosProdutos depositoProduto = new DepositosProdutos();

                depositoProduto.setCodigoDeposito(rs.getString(1));
                depositoProduto.setDeposito(rs.getString(2));
                depositoProduto.setCodigoProduto(rs.getString(3));
                depositoProduto.setProduto(rs.getString(4));
                depositoProduto.setQuantidade(rs.getString(5));
                depositoProduto.setQuantidadeMinima(rs.getString(6));

                depositosProdutos.add(depositoProduto);
            }

            rs.close();
            ps.close();
            conn.close();

            return depositosProdutos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositosProdutos;
    }

    public List<DepositosProdutos> getProdutosDoDeposito(String codigoDeposito, String url, String user,
            String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_codigo_desposito, nom_desposito, nom_codigo_produto, nom_produto, num_quantidade, num_quantidade_minima "
                            +
                            "from vw_listar_depositos_produtos " +
                            "where nom_codigo_desposito = '" + codigoDeposito + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                DepositosProdutos depositoProduto = new DepositosProdutos();

                depositoProduto.setCodigoDeposito(rs.getString(1));
                depositoProduto.setDeposito(rs.getString(2));
                depositoProduto.setCodigoProduto(rs.getString(3));
                depositoProduto.setProduto(rs.getString(4));
                depositoProduto.setQuantidade(rs.getString(5));
                depositoProduto.setQuantidadeMinima(rs.getString(6));

                depositosProdutos.add(depositoProduto);
            }

            rs.close();
            ps.close();
            conn.close();

            return depositosProdutos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositosProdutos;
    }

    public DepositosProdutos getDepositoProduto(String codigoDeposito, String codigoProduto, String url, String user,
            String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        DepositosProdutos depositoProduto = new DepositosProdutos();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_codigo_desposito, nom_desposito, nom_codigo_produto, nom_produto, num_quantidade, num_quantidade_minima "
                            +
                            "from vw_listar_depositos_produtos " +
                            "where nom_codigo_desposito = '" + codigoDeposito + "' and nom_codigo_produto = '"
                            + codigoProduto + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                depositoProduto.setCodigoDeposito(rs.getString(1));
                depositoProduto.setDeposito(rs.getString(2));
                depositoProduto.setCodigoProduto(rs.getString(3));
                depositoProduto.setProduto(rs.getString(4));
                depositoProduto.setQuantidade(rs.getString(5));
                depositoProduto.setQuantidadeMinima(rs.getString(6));
            }

            rs.close();
            ps.close();
            conn.close();

            return depositoProduto;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            depositoProduto.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            depositoProduto.setMensagem("Erro");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositoProduto;
    }

    public List<DepositosProdutos> getDepositosComProduto(String codigoProduto, String url, String user,
            String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_codigo_desposito, nom_desposito, nom_codigo_produto, nom_produto, num_quantidade, num_quantidade_minima "
                            +
                            "from vw_listar_depositos_produtos " +
                            "where nom_codigo_produto = '" + codigoProduto + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                DepositosProdutos depositoProduto = new DepositosProdutos();

                depositoProduto.setCodigoDeposito(rs.getString(1));
                depositoProduto.setDeposito(rs.getString(2));
                depositoProduto.setCodigoProduto(rs.getString(3));
                depositoProduto.setProduto(rs.getString(4));
                depositoProduto.setQuantidade(rs.getString(5));
                depositoProduto.setQuantidadeMinima(rs.getString(6));

                depositosProdutos.add(depositoProduto);
            }

            rs.close();
            ps.close();
            conn.close();

            return depositosProdutos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            DepositosProdutos depositoProduto = new DepositosProdutos();
            depositoProduto.setMensagem("Erro");
            depositosProdutos.add(depositoProduto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return depositosProdutos;
    }

    public List<Movimentacoes> getMovimentacoes(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Movimentacoes> movimentacoes = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select num_codigo, nom_movimentacao, nom_codigo_deposito, nom_deposito, nom_codigo_produto, nom_produto, num_quantidade, dec_valor_unitario, dec_valor_total, dat_registro "
                            +
                            "from vw_listar_movimentacoes");
            rs = ps.executeQuery();

            while (rs.next()) {
                Movimentacoes movimentacao = new Movimentacoes();

                movimentacao.setCodigo(rs.getString(1));
                movimentacao.setMovimentacao(rs.getString(2));
                movimentacao.setCodigoDeposito(rs.getString(3));
                movimentacao.setDeposito(rs.getString(4));
                movimentacao.setCodigoProduto(rs.getString(5));
                movimentacao.setProduto(rs.getString(6));
                movimentacao.setQuantidade(rs.getString(7));
                movimentacao.setValorUnitario(rs.getFloat(8));
                movimentacao.setValorTotal(rs.getString(9));
                movimentacao.setRegistro(rs.getString(10));

                movimentacoes.add(movimentacao);
            }

            rs.close();
            ps.close();
            conn.close();

            return movimentacoes;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Movimentacoes movimentacao = new Movimentacoes();
            movimentacao.setMensagem("Erro");
            movimentacoes.add(movimentacao);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Movimentacoes movimentacao = new Movimentacoes();
            movimentacao.setMensagem("Erro");
            movimentacoes.add(movimentacao);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return movimentacoes;
    }

    public List<ProdutosFornecedores> getProdutosFornecedores(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_produto, nom_codigo_produto, nom_fornecedor, nom_codigo_fornecedor " +
                            "from vw_listar_produtos_fornecedores");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();

                produtoFornecedor.setProduto(rs.getString(1));
                produtoFornecedor.setCodigoProduto(rs.getString(2));
                produtoFornecedor.setFornecedor(rs.getString(3));
                produtoFornecedor.setCodigoFornecedor(rs.getString(4));

                produtosFornecedores.add(produtoFornecedor);
            }

            rs.close();
            ps.close();
            conn.close();

            return produtosFornecedores;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produtosFornecedores;
    }

    public List<ProdutosFornecedores> getProdutosDoFornecedor(String codigo, String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_produto, nom_codigo_produto, nom_fornecedor, nom_codigo_fornecedor " +
                            "from vw_listar_produtos_fornecedores " +
                            "where nom_codigo_fornecedor = '" + codigo + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();

                produtoFornecedor.setProduto(rs.getString(1));
                produtoFornecedor.setCodigoProduto(rs.getString(2));
                produtoFornecedor.setFornecedor(rs.getString(3));
                produtoFornecedor.setCodigoFornecedor(rs.getString(4));

                produtosFornecedores.add(produtoFornecedor);
            }

            rs.close();
            ps.close();
            conn.close();

            return produtosFornecedores;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produtosFornecedores;
    }

    public List<ProdutosFornecedores> getFornecedoresDoProduto(String codigo, String url, String user,
            String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select nom_produto, nom_codigo_produto, nom_fornecedor, nom_codigo_fornecedor " +
                            "from vw_listar_produtos_fornecedores " +
                            "where nom_codigo_produto = '" + codigo + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();

                produtoFornecedor.setProduto(rs.getString(1));
                produtoFornecedor.setCodigoProduto(rs.getString(2));
                produtoFornecedor.setFornecedor(rs.getString(3));
                produtoFornecedor.setCodigoFornecedor(rs.getString(4));

                produtosFornecedores.add(produtoFornecedor);
            }

            rs.close();
            ps.close();
            conn.close();

            return produtosFornecedores;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            ProdutosFornecedores produtoFornecedor = new ProdutosFornecedores();
            produtoFornecedor.setMensagem("Erro");
            produtosFornecedores.add(produtoFornecedor);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produtosFornecedores;
    }

    public List<Fornecedores> getFornecedores(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Fornecedores> fornecedores = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement("select id, nom_nome, nom_codigo from es_fornecedores");
            rs = ps.executeQuery();

            while (rs.next()) {
                Fornecedores fornecedor = new Fornecedores();

                fornecedor.setId(rs.getString(1));
                fornecedor.setNome(rs.getString(2));
                fornecedor.setCodigo(rs.getString(3));

                fornecedores.add(fornecedor);
            }

            rs.close();
            ps.close();
            conn.close();

            return fornecedores;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Fornecedores fornecedor = new Fornecedores();
            fornecedor.setMensagem("Erro");
            fornecedores.add(fornecedor);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Fornecedores fornecedor = new Fornecedores();
            fornecedor.setMensagem("Erro");
            fornecedores.add(fornecedor);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return fornecedores;
    }

    public List<Produtos> getProdutos(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select id, nom_nome, nom_codigo, num_quantidade_minima, dec_valor_medio, num_quantidade " +
                            "from vw_listar_produtos");
            rs = ps.executeQuery();

            while (rs.next()) {
                Produtos produto = new Produtos();

                produto.setId(rs.getString(1));
                produto.setNome(rs.getString(2));
                produto.setCodigo(rs.getString(3));
                produto.setQuantidadeMinima(rs.getString(4));
                produto.setValorMedio(rs.getString(5));
                produto.setQuantidade(rs.getString(6));

                produtos.add(produto);
            }

            rs.close();
            ps.close();
            conn.close();

            return produtos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Produtos produto = new Produtos();
            produto.setMensagem("Erro");
            produtos.add(produto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Produtos produto = new Produtos();
            produto.setMensagem("Erro");
            produtos.add(produto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produtos;
    }

    public List<Produtos> getProdutosParaRepor(String url, String user, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, user, password);

            ps = conn.prepareStatement(
                    "select id, nom_nome, nom_codigo, num_quantidade_minima, dec_valor_medio, num_quantidade " +
                            "from vw_listar_produtos " +
                            "where num_quantidade < num_quantidade_minima");
            rs = ps.executeQuery();

            while (rs.next()) {
                Produtos produto = new Produtos();

                produto.setId(rs.getString(1));
                produto.setNome(rs.getString(2));
                produto.setCodigo(rs.getString(3));
                produto.setQuantidadeMinima(rs.getString(4));
                produto.setValorMedio(rs.getString(5));
                produto.setQuantidade(rs.getString(6));

                produtos.add(produto);
            }

            rs.close();
            ps.close();
            conn.close();

            return produtos;
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Produtos produto = new Produtos();
            produto.setMensagem("Erro");
            produtos.add(produto);
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);

            Produtos produto = new Produtos();
            produto.setMensagem("Erro");
            produtos.add(produto);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produtos;
    }

    public Depositos postDeposito(Depositos deposito, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Depositos ret = new Depositos();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("insert into es_depositos (nom_nome, nom_codigo) " +
                    "values ('" + deposito.getNome() + "', '" + deposito.getCodigo() + "')");
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Deposito cadastrado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public ProdutosFornecedores postProdutoFornecedor(ProdutosFornecedores produtoFornecedor, String url,
            String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        ProdutosFornecedores ret = new ProdutosFornecedores();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("{call prc_registrar_produto_fornecedor(?, ?)}");
            ps.setString(1, produtoFornecedor.getCodigoProduto());
            ps.setString(2, produtoFornecedor.getCodigoFornecedor());
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Produto e fornecedor cadastrado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public Movimentacoes postMovimentacao(String codigoDeposito, String codigoProduto, Movimentacoes movimentacao,
            String url,
            String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Movimentacoes ret = new Movimentacoes();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("{call prc_atualizar_quantidade_produto_deposito(?, ?, ?, ?, ?)}");
            ps.setString(1, codigoDeposito);
            ps.setString(2, codigoProduto);
            ps.setString(3, movimentacao.getQuantidade());
            ps.setString(4, movimentacao.getCodigo());

            if (movimentacao.getValorUnitario() == null) {
                movimentacao.setValorUnitario(0f);
            }

            ps.setFloat(5, movimentacao.getValorUnitario());

            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Movimentação registrada!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public Movimentacoes postMovimentacao(String codigoDeposito, Movimentacoes movimentacao, String url,
            String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Movimentacoes ret = new Movimentacoes();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("{call prc_adicionar_produto_deposito(?, ?, ?, ?, ?, ?)}");
            ps.setString(1, codigoDeposito);
            ps.setString(2, movimentacao.getCodigoProduto());
            ps.setString(3, movimentacao.getQuantidade());
            ps.setString(4, movimentacao.getQuantidadeMinima());
            ps.setString(5, movimentacao.getCodigo());

            if (movimentacao.getValorUnitario() == null) {
                movimentacao.setValorUnitario(0f);
            }

            ps.setFloat(6, movimentacao.getValorUnitario());

            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Movimentação registrada!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public Fornecedores postFornecedor(Fornecedores fornecedor, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Fornecedores ret = new Fornecedores();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("insert into es_fornecedores (nom_nome, nom_codigo) " +
                    "values ('" + fornecedor.getNome() + "', '" + fornecedor.getCodigo() + "')");
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Fornecedor cadastrado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public Produtos postProduto(Produtos produto, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Produtos ret = new Produtos();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("insert into es_produtos (nom_nome, nom_codigo, num_quantidade_minima) " +
                    "values ('" + produto.getNome() + "', '" + produto.getCodigo() + "', '"
                    + produto.getQuantidadeMinima() + "')");
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Produto cadastrado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return ret;
    }

    public Depositos putDeposito(String id, Depositos deposito, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Depositos ret = new Depositos();
        List<String> partial_command = new ArrayList<>();

        if (deposito.getNome() != null) {
            partial_command.add("nom_nome = '" + deposito.getNome() + "'");
        }

        if (deposito.getCodigo() != null) {
            partial_command.add("nom_codigo = '" + deposito.getCodigo() + "'");
        }

        String command = "update es_depositos set " +
                String.join(", ", partial_command) + " where id = " + id;

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall(command);
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Deposito alterado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ret;
    }

    public Fornecedores putFornecedor(String id, Fornecedores fornecedor, String url, String username,
            String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Fornecedores ret = new Fornecedores();
        List<String> partial_command = new ArrayList<>();

        if (fornecedor.getNome() != null) {
            partial_command.add("nom_nome = '" + fornecedor.getNome() + "'");
        }

        if (fornecedor.getCodigo() != null) {
            partial_command.add("nom_codigo = '" + fornecedor.getCodigo() + "'");
        }

        String command = "update es_fornecedores set " +
                String.join(", ", partial_command) + " where id = " + id;

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall(command);
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Fornecedor alterado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ret;
    }

    public Produtos putProduto(String id, Produtos produto, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Produtos ret = new Produtos();
        List<String> partial_command = new ArrayList<>();

        if (produto.getNome() != null) {
            partial_command.add("nom_nome = '" + produto.getNome() + "'");
        }

        if (produto.getCodigo() != null) {
            partial_command.add("nom_codigo = '" + produto.getCodigo() + "'");
        }

        if (produto.getQuantidadeMinima() != null) {
            partial_command.add("num_quantidade_minima = '" + produto.getQuantidadeMinima() + "'");
        }

        String command = "update es_produtos set " +
                String.join(", ", partial_command) + " where id = " + id;

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall(command);
            ps.executeUpdate();

            ps.close();
            conn.close();

            ret.setMensagem("Produto alterado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            ret.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ret;
    }

    public Depositos deleteDeposito(String id, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Depositos deposito = new Depositos();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("delete es_depositos " +
                    "where id = " + id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            deposito.setMensagem("Depósito apagado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            deposito.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            deposito.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return deposito;
    }

    public Fornecedores deleteFornecedor(String id, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Fornecedores fornecedor = new Fornecedores();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("delete es_fornecedores " +
                    "where id = " + id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            fornecedor.setMensagem("Fornecedor apagado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            fornecedor.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            fornecedor.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return fornecedor;
    }

    public Produtos deleteProduto(String id, String url, String username, String password) {
        Connection conn = null;
        CallableStatement ps = null;

        Produtos produto = new Produtos();

        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareCall("delete es_produtos " +
                    "where id = " + id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            produto.setMensagem("Produto apagado!");
        } catch (SQLException e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            produto.setMensagem("Erro");
        } catch (Exception e) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
            produto.setMensagem("Erro");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return produto;
    }
}

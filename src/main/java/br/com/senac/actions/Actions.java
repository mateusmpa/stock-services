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

public class Actions {
    public Depositos busca(String codigo, String url, String user, String password) {
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

    public List<Depositos> buscaLista(String url, String user, String password) {
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
}

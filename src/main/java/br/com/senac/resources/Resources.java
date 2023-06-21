package br.com.senac.resources;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.senac.actions.Actions;
import br.com.senac.entities.Depositos;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estoque")
public class Resources {
    @ConfigProperty(name = "quarkus.datasource.url")
    String url;

    @ConfigProperty(name = "quarkus.datasource.username")
    String username;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos")
    public List<Depositos> getDepositos() {
        Actions action = new Actions();
        List<Depositos> depositos = new ArrayList<>();

        depositos = action.buscaLista(url, username, password);

        return depositos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos/{id}")
    public Depositos getDeposito(@PathParam("id") String id) {
        Actions action = new Actions();
        Depositos deposito = new Depositos();

        deposito = action.busca(id, url, username, password);

        return deposito;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos")
    public Depositos postDeposito(Depositos deposito) {
        Actions action = new Actions();
        Depositos ret = new Depositos();

        ret = action.postDeposito(deposito, url, username, password);

        return ret;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{id}")
    public Depositos putDeposito(@PathParam("id") String id, Depositos deposito) {
        Actions action = new Actions();
        Depositos ret = new Depositos();

        ret = action.putDeposito(id, deposito, url, username, password);

        return ret;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{id}")
    public Depositos deleteDeposito(@PathParam("id") String id) {
        Actions action = new Actions();
        Depositos deposito = new Depositos();

        deposito = action.deleteDeposito(id, url, username, password);

        return deposito;
    }
}

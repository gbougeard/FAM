package org.fam.ws;

import lombok.Setter;
import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamClub;
import org.fam.ejb.session.FamClubFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 17/03/12
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Path("/")
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
@Setter
public class FamClubService {

    public static final String PATH = "club";

    @Inject
    private FamClubFacade ejbClub;

    @Context
    private UriInfo context;

    @GET
    @Path(PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FamClub> all() {
        return this.ejbClub.findAll();
    }

    @GET
    @Path(PATH + "/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public FamClub getJSON(@PathParam("id") int id) {
        return this.ejbClub.find(new Integer(id));
    }
}

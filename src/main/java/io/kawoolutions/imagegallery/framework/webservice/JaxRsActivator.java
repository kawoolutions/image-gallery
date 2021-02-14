package io.kawoolutions.imagegallery.framework.webservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath( "/ws" )
public class JaxRsActivator extends Application
{
    // activates JAX-RS for path /ws -> localhost:8080/bbstats/ws
}

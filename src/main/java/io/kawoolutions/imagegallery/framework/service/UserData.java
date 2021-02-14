package io.kawoolutions.imagegallery.framework.service;

import javax.inject.Named;

import io.kawoolutions.imagegallery.framework.cdi.BaseCdiBean;

@Named
public class UserData extends BaseCdiBean
{
    private static final long serialVersionUID = 1L;
    
    // TODO: architecture alert: service layer references business layer
//    @Inject
//    private SessionHelper sessionHelper;
    
    
}

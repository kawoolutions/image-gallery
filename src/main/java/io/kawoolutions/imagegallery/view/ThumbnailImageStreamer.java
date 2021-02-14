package io.kawoolutions.imagegallery.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import io.kawoolutions.imagegallery.entity.GalleryImage;
import io.kawoolutions.imagegallery.entity.ThumbnailImage;
import io.kawoolutions.imagegallery.service.GalleryImageService;

@Named
@RequestScoped
public class ThumbnailImageStreamer implements Serializable
{
    private static final long serialVersionUID = -8037553827082927709L;
    
    @Inject
    private ThumbnailImageManager thumbnailImageManager;
    
    @Inject
    private GalleryImageService galleryImageService;
    
    @PostConstruct
    public void construct()
    {
        System.out.println(ThumbnailImageStreamer.class.getSimpleName() + ": @PostConstruct!");
    }

    public StreamedContent getThumbnailImageStream()
    {
//        System.out.println(ThumbnailImageStreamer.class.getSimpleName() + ": getThumbnailImageStream()!");

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
//            System.out.println("RENDER_RESPONSE");

            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }

        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get( "thumbnailImageId" ));
        ThumbnailImage tim = thumbnailImageManager.getCachedEntitiesMap().get( id );
        
        System.out.println("Returning streamed content: bytes = " + tim.getData().length);
        
        // So, browser is requesting the image. Get ID value from actual request param.
        return new DefaultStreamedContent(new ByteArrayInputStream(tim.getData()), tim.getMimeType());
    }

    public StreamedContent getGalleryImageStream()
    {
        System.out.println(ThumbnailImageStreamer.class.getSimpleName() + ": getGalleryImageStream()!");

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
        {
//            System.out.println("RENDER_RESPONSE");

            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }

        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get( "galleryImageId" ));
        GalleryImage gim = galleryImageService.findByPk( id );
        
        System.out.println("Returning streamed content: bytes = " + gim.getData().length);

        // So, browser is requesting the image. Get ID value from actual request param.
        return new DefaultStreamedContent(new ByteArrayInputStream(gim.getData()), gim.getMimeType());
    }
}

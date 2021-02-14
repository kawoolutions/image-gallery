package io.kawoolutions.imagegallery.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.galleria.Galleria;

import io.kawoolutions.imagegallery.entity.ThumbnailImage;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;
import io.kawoolutions.imagegallery.framework.management.BaseSelectionHandler;
import io.kawoolutions.imagegallery.service.ThumbnailImageService;

@Named
@SessionScoped
public class ThumbnailImageManager extends BaseSelectionHandler<ThumbnailImage>
{
    private static final long serialVersionUID = -5591646034578365251L;
    
    @Inject
    private ThumbnailImageService thumbnailImageService;
    
    private Map<Integer, ThumbnailImage> cachedEntitiesMap;
    
    @PostConstruct
    public void construct()
    {
        System.out.println(ThumbnailImageManager.class.getSimpleName() + ": @PostConstruct!");
    }

    @Override
    protected List<ThumbnailImage> loadEntities() throws RetrieveException
    {
        List<ThumbnailImage> entities = null;
        
        try
        {
            entities = thumbnailImageService.findAll();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return entities;
    }
    
    public Map<Integer, ThumbnailImage> getCachedEntitiesMap()
    {
        if ( cachedEntitiesMap == null )
        {
            cachedEntitiesMap = new HashMap<>();
            
            getEntities().forEach( t -> cachedEntitiesMap.put( t.getId(), t ) );
        }
        
        return cachedEntitiesMap;
    }
    
    public void selectEntityAt()
    {
        Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String indexStr = requestParamMap.get("selectedGalleryImageIndex");
//        System.out.println("Selecting nth: " + indexStr);
        
        Galleria g = null;
        
        setSelectedEntity(getEntities().get(Integer.valueOf( indexStr ).intValue()));
    }

    public void selectPreviousEntity()
    {
        int selectedIndex = entities.indexOf(getSelectedEntity());
//        int previousIndex = selectedIndex == 0 ? entities.size() - 1 : selectedIndex - 1;
        if ( selectedIndex == 0 )
        {
            return;
        }
        
        setSelectedEntity(entities.get(selectedIndex - 1));
    }

    public void selectNextEntity()
    {
        int selectedIndex = entities.indexOf(getSelectedEntity());
        int nextIndex = selectedIndex == entities.size() - 1 ? 0 : selectedIndex + 1;
        setSelectedEntity(entities.get(nextIndex));
    }
}

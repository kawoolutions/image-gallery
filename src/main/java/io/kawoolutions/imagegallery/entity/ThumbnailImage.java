package io.kawoolutions.imagegallery.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.kawoolutions.imagegallery.framework.entity.BaseIdEntity;

@Entity
@Table(name = "\"ThumbnailImages\"")
@NamedQuery(name = ThumbnailImage.FIND_ALL, query = "SELECT ti FROM ThumbnailImage ti")
public class ThumbnailImage extends BaseIdEntity
{
    private static final long serialVersionUID = 1L;
    
    public static final String FIND_ALL = "ThumbnailImage.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Basic(optional = false)
    @Column(name = "file_name")
    private String fileName;

    @Basic(optional = false)
    @Column(name = "file_type")
    private String fileType;

    @Basic(optional = false)
    @Column(name = "mime_type")
    private String mimeType;

    @Basic(optional = false)
    @Column
    @Lob
    private byte[] data;

    @Basic(optional = false)
    @Column
    private Integer width;

    @Basic(optional = false)
    @Column
    private Integer height;

    @Basic
    @Column(name = "alternative_text")
    private String alternativeText;

    @Basic
    @Column
    private String remarks;

    @OneToOne(mappedBy = "thumbnailImage", optional = false, fetch = FetchType.LAZY)
    private GalleryImage galleryImage;

    public ThumbnailImage()
    {
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public Integer getWidth()
    {
        return width;
    }

    public void setWidth(Integer width)
    {
        this.width = width;
    }

    public Integer getHeight()
    {
        return height;
    }

    public void setHeight(Integer height)
    {
        this.height = height;
    }
    
    public double getAspectRatio()
    {
        double aspectRatio = (double) width / height;
        
        return aspectRatio;
    }

    public String getAlternativeText()
    {
        return alternativeText;
    }

    public void setAlternativeText(String alternativeText)
    {
        this.alternativeText = alternativeText;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public GalleryImage getGalleryImage()
    {
        return galleryImage;
    }

    public void setGalleryImage(GalleryImage galleryImage)
    {
        this.galleryImage = galleryImage;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( obj == null )
        {
            return false;
        }

        if ( getClass() != obj.getClass() )
        {
            return false;
        }

        ThumbnailImage other = ( ThumbnailImage ) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id);
    }

    @Override
    public String toString()
    {
        return "ThumbnailImage [id=" + Objects.toString(this.id) + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", mimeType=" + this.mimeType + ", width=" + Objects.toString(this.width) + ", height=" + Objects.toString(this.height) + ", alternativeText=" + this.alternativeText + ", remarks=" + this.remarks + "]";
    }
}

package io.kawoolutions.imagegallery.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.kawoolutions.imagegallery.framework.entity.BaseIdEntity;

@Entity
@Table(name = "\"GalleryImages\"")
public class GalleryImage extends BaseIdEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, updatable = false)
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

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ThumbnailImage thumbnailImage;

    public GalleryImage()
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

    public ThumbnailImage getThumbnailImage()
    {
        return thumbnailImage;
    }

    public void setThumbnailImage(ThumbnailImage thumbnailImage)
    {
        this.thumbnailImage = thumbnailImage;
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

        GalleryImage other = ( GalleryImage ) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.thumbnailImage, other.thumbnailImage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.thumbnailImage);
    }

    @Override
    public String toString()
    {
        return "GalleryImage [id=" + Objects.toString(this.id) + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", mimeType=" + this.mimeType + ", width=" + Objects.toString(this.width) + ", height=" + Objects.toString(this.height) + "]";
    }
}

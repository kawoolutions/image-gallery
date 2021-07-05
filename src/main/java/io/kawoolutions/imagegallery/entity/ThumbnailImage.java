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
@Table(name = "\"ThumbnailImages\"")
public class ThumbnailImage extends BaseIdEntity
{
    private static final long serialVersionUID = 1L;

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

    @Basic(optional = false, fetch = FetchType.LAZY)
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
    @Column(name = "remarks_preview")
    private String remarksPreview;

    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private FullsizeImage fullsizeImage;

    public ThumbnailImage()
    {
    }

    @Override
    public Integer getPk()
    {
        return fullsizeImage.getPk();
    }

    @Override
    public void setPk(Integer pk)
    {
        fullsizeImage.setPk(pk);
    }

    @Override
    public Integer getId()
    {
        return fullsizeImage.getId();
    }

    @Override
    public void setId(Integer id)
    {
        fullsizeImage.setId(id);
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

    public String getAlternativeText()
    {
        return alternativeText;
    }

    public void setAlternativeText(String alternativeText)
    {
        this.alternativeText = alternativeText;
    }

    public String getRemarksPreview()
    {
        return remarksPreview;
    }

    public void setRemarksPreview(String remarksPreview)
    {
        this.remarksPreview = remarksPreview;
    }

    public FullsizeImage getFullsizeImage()
    {
        return fullsizeImage;
    }

    public void setFullsizeImage(FullsizeImage fullsizeImage)
    {
        this.fullsizeImage = fullsizeImage;
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

        return Objects.equals(this.fullsizeImage, other.fullsizeImage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.fullsizeImage);
    }

    @Override
    public String toString()
    {
        return "ThumbnailImage [id=" + Objects.toString(this.id) + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", mimeType=" + this.mimeType + ", width=" + Objects.toString(this.width) + ", height=" + Objects.toString(this.height) + ", alternativeText=" + this.alternativeText + ", remarksPreview=" + this.remarksPreview + "]";
    }
}

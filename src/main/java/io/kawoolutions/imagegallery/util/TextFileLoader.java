package io.kawoolutions.imagegallery.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.faces.context.FacesContext;

public class TextFileLoader
{
    public static String readFile( File fl )
    {
        String content = null;
        FileInputStream fis = null;
        
        try
        {
            fis = new FileInputStream( fl );
            byte[] readBuffer = new byte[( int ) fl.length()];
            
            fis.read( readBuffer );
            content = new String( readBuffer );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if ( fis != null )
            {
                try
                {
                    fis.close();
                }
                catch ( Throwable t )
                {
                    System.err.println( "File input stream to " + fl + " couldn't be closed!" );
                }
            }
        }
        
        return content;
    }
    
    public static String readResource( String resource )
    {
        String content = "";
        
        InputStream is = null;
        BufferedReader br = null;
        
        try
        {
            // System.out.println("Class path = \n" +
            // RuntimeInfo.getJavaClassPath());
            
            // URL url = TextFileLoader.class.getResource(sResource);
            // System.out.println("-------------------- Resource string = " +
            // sResource + " => URL = " + url);
            
            is = TextFileLoader.class.getResourceAsStream( resource );
            
            // resource not found, check web environment
            if ( is == null )
            {
                is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream( resource );
            }
            
            is.available();
            
            br = new BufferedReader( new InputStreamReader( is ) );
            
            String line = null;
            
            while ( ( line = br.readLine() ) != null )
            {
                content += line + "\n";
            }
            
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if ( is != null )
            {
                try
                {
                    is.close();
                }
                catch ( Throwable t )
                {
                    System.err.println( "Input stream to " + resource + " couldn't be closed!" );
                }
            }
        }
        
        return content;
    }
}

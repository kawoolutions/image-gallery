package io.kawoolutions.imagegallery.framework.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class HashUtils
{
    private static final String CHAR_TO_BYTE_CONVERTER = "UTF-8";
    
    private static final String HASH_ALGORITHM_SHA_1   = "SHA-1";
    private static final String HASH_ALGORITHM_SHA_256 = "SHA-256";
//    private static final String HASH_ALGORITHM_SHA_384 = "SHA-384";
//    private static final String HASH_ALGORITHM_SHA_512 = "SHA-512";

    // to generate the salt
    private static final SecureRandom secureRandom = new SecureRandom();
    
    public static final String createHashedStringSha1( String plaintextPassword )
    {
        MessageDigest digest;
        byte[] hashedBytes;
        
        try
        {
            digest = MessageDigest.getInstance( HASH_ALGORITHM_SHA_1 );
        }
        catch ( NoSuchAlgorithmException nsae )
        {
            throw new IllegalArgumentException( "Der übergebene Hash-Algorithmus ist unbekannt: " + HASH_ALGORITHM_SHA_1, nsae );
        }
        
        try
        {
            hashedBytes = digest.digest( plaintextPassword.getBytes( CHAR_TO_BYTE_CONVERTER ) );
        }
        catch ( UnsupportedEncodingException e )
        {
            throw new IllegalArgumentException( "Unbekanntes Character-Encoding: " + CHAR_TO_BYTE_CONVERTER, e );
        }
        
        return new String( Base64.getEncoder().encodeToString( hashedBytes ) );
    }
    
    
    public static final String createHashedStringSha256( String plaintextPassword )
    {
        MessageDigest digest;
        byte[] hashedBytes;
        
        try
        {
            digest = MessageDigest.getInstance( HASH_ALGORITHM_SHA_256 );
        }
        catch ( NoSuchAlgorithmException nsae )
        {
            throw new IllegalArgumentException( "Der übergebene Hash-Algorithmus ist unbekannt: " + HASH_ALGORITHM_SHA_256, nsae );
        }
        
        try
        {
            hashedBytes = digest.digest( plaintextPassword.getBytes( CHAR_TO_BYTE_CONVERTER ) );
        }
        catch ( UnsupportedEncodingException e )
        {
            throw new IllegalArgumentException( "Unbekanntes Character-Encoding: " + CHAR_TO_BYTE_CONVERTER, e );
        }
        
        return new String( Base64.getEncoder().encodeToString( hashedBytes ) );
    }
    
    public static final String createHashedStringPBKDF2withHmacSHA512( byte[] salt, String plaintextPassword ) throws InvalidKeySpecException
    {
        byte[] hashedBytes;
        
        try
        {
            KeySpec keySpec = new PBEKeySpec( plaintextPassword.toCharArray(), salt, 20000, 128 );
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            
            hashedBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();
        }
        catch ( NoSuchAlgorithmException nsae )
        {
            throw new IllegalArgumentException( "Der übergebene Algorithmus ist unbekannt: PBKDF2WithHmacSHA512 ", nsae );
        }
         
        return new String( Base64.getEncoder().encodeToString( hashedBytes ) );
    }
    
    public static final byte[] createSalt()
    {
        byte[] salt = new byte[16];
        
        secureRandom.nextBytes( salt );
         
        return salt;
    }
}

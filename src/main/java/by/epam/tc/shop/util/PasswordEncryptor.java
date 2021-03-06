package by.epam.tc.shop.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * The {@code PasswordEncryptor} is used to encrypt passwords with SHA-256 algo
 *
 * @author Pavel Voronin
 * @version 1.0
 */
public class PasswordEncryptor {
    private static final Logger logger = LogManager.getLogger();
    private static final String ENCRYPTOR_ALGORITHM = "SHA-256";

    private PasswordEncryptor(){}

    /**
     * Encrypt Password
     *
     * @param password not encrypted password string
     * @return encrypted
     */
    public static Optional<String> encryptPassword(String password){
        Optional<String> encPassword = Optional.empty();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTOR_ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytesEncoded = messageDigest.digest();

            BigInteger bigInteger = new BigInteger(1,bytesEncoded);
            encPassword = Optional.of(bigInteger.toString(16));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while encrypting password",e);
        }
        return encPassword;
    }
}

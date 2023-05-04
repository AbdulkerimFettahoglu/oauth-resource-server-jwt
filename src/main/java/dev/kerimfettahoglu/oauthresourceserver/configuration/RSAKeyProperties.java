package dev.kerimfettahoglu.oauthresourceserver.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * To create public-private key pair use following commands:
 *
 * # create rsa key pair
 * openssl genrsa -out keypair.pem 2048
 *
 * # extract public key
 * openssl rsa -in keypair.pem -pubout -out public.pem
 *
 * # create private key in PKCS#8 format
 * openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "rsa")
public class RSAKeyProperties {
    RSAPublicKey publicKey;
    RSAPrivateKey privateKey;
}

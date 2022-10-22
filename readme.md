# Spring security with JWTs authentication

Jwt token generation (its already there)

https://www.baeldung.com/spring-security-oauth-jwt

keytool -genkeypair -alias jwt -keyalg RSA -keypass 123456 -keystore demo.jks -storepass 123456

keytool -importkeystore -srckeystore demo.jks -destkeystore jwt.jks -deststoretype pkcs12

keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey

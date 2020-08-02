package co.nitin.todo.constants;

public class SecurityConstants {

	public static final String PBKDF2_HASH_SECRET = "thisismysecret";
	public static final Integer PBKDF2_HASH_ITERATION = 1000;
	public static final Integer PBKDF2_HASH_WIDTH = 20;
	
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}

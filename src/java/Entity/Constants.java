package Entity;

import java.io.Serializable;

/**
 * Constants class to store application constants.
 * Replace hardcoded values with environment variables.
 */
public class Constants implements Serializable {

    // Define environment variable names
    private static final String ENV_GOOGLE_CLIENT_ID = "GOOGLE_CLIENT_ID";
    private static final String ENV_GOOGLE_CLIENT_SECRET = "GOOGLE_CLIENT_SECRET";

    // Get Google OAuth client ID from environment variable
    public static String GOOGLE_CLIENT_ID = System.getenv(ENV_GOOGLE_CLIENT_ID);

    // Get Google OAuth client secret from environment variable
    public static String GOOGLE_CLIENT_SECRET = System.getenv(ENV_GOOGLE_CLIENT_SECRET);

    // Define other constants
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/QuizzesOnline/LoginGoogleURL";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}

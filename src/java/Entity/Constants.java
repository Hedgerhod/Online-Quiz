/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Constants implements Serializable {

    public static String GOOGLE_CLIENT_ID = "792958604853-t1da5rg9q5fblc145j7a30814recg0js.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/QuizzesOnline/LoginGoogleURL";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}

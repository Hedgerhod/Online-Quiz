/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listener;

import Model.DBConnect;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author hieul
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying........");
        ServletContext context = sce.getServletContext();
        String siteMapFile = context.getInitParameter("SITE_MAP_FILE");
        String roleMapFile = context.getInitParameter("ROLE_MAP_FILE");

        try {
            Properties siteMaps = DBConnect.getSiteMaps(siteMapFile, context);
            context.setAttribute("SITE_MAPS", siteMaps);

            Properties roleMaps = DBConnect.getSiteMaps(roleMapFile, context);
            context.setAttribute("ROLE_MAPS", roleMaps);
        } catch (IOException ex) {
            context.log("Context Listener IO" + ex.getMessage()); // nhung noi khac servlet, dung context scope to log
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeployed.");
    }

}

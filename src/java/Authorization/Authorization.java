/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authorization;

import Utils.MyApplicationConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author hieul
 */
public class Authorization {
     private Map<String, String> featureNavs;
        private static final String HOME_FEATURE_LABEL = "Home";
        private static final String LOGIN_FEATURE_LABEL = "Login";
        private static final String LOGOUT_FEATURE_LABEL = "Logout";
        private static final String ACCOUNT_MANAGEMENT_FEATURE_LABEL = "Account Management";
        private static final String ADMIN_FEATURE_LABEL = "Admin";
        private static final String QUESTIONSET_FEATURE_LABEL = "Question set";
        private static final String CLASS_LABEL = "Class";
        private static final String EXAM_LABEL = "Exam";
        private static String[] adminFeatures = {
            HOME_FEATURE_LABEL,
            ACCOUNT_MANAGEMENT_FEATURE_LABEL,
            ADMIN_FEATURE_LABEL,
            LOGOUT_FEATURE_LABEL
        };
        private static String[] studentFeatures = {
            HOME_FEATURE_LABEL,
            ACCOUNT_MANAGEMENT_FEATURE_LABEL,
            QUESTIONSET_FEATURE_LABEL,
            CLASS_LABEL,
            EXAM_LABEL,
            LOGOUT_FEATURE_LABEL
        };
        private static String[] teacherFeatures = {
            HOME_FEATURE_LABEL,
            ACCOUNT_MANAGEMENT_FEATURE_LABEL,
            QUESTIONSET_FEATURE_LABEL,
            CLASS_LABEL,
            EXAM_LABEL,
            LOGOUT_FEATURE_LABEL
        };
        private static String[] guestFeatures = {
            HOME_FEATURE_LABEL,
            QUESTIONSET_FEATURE_LABEL,
            LOGIN_FEATURE_LABEL,
            LOGOUT_FEATURE_LABEL
        };

        public Authorization() {
            featureNavs = new HashMap<>();
            featureNavs.put(HOME_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.HOME_ACTION);
            featureNavs.put(LOGIN_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.LOGIN_ACTION);
            featureNavs.put(LOGOUT_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.LOGOUT_ACTION);
            featureNavs.put(ACCOUNT_MANAGEMENT_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.ACCOUNT_MANAGE_ACTION);
            featureNavs.put(ADMIN_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.ADMIN_ACTION);
            featureNavs.put(QUESTIONSET_FEATURE_LABEL, MyApplicationConstants.ApplicationScope.QUESTION_SET_ACTION);
            featureNavs.put(CLASS_LABEL, MyApplicationConstants.ApplicationScope.CLASS_ACTION);
            featureNavs.put(EXAM_LABEL, MyApplicationConstants.ApplicationScope.EXAM_ACTION);
        }

        public List<Pair<String, String>> getFeatureNavs(int role) {
            List<Pair<String, String>> result = new ArrayList<>();
            String[] features = null;
            switch (role) {
                case 0:
                    features = guestFeatures;
                    break;
                case 1:
                    features = studentFeatures;
                    break;
                case 2:
                    features = teacherFeatures;
                    break;
                case 3:
                    features = adminFeatures;
                    break;
            }
            for (String feature : features) {
                Pair<String, String> pair = new Pair<>(feature, featureNavs.get(feature));
                result.add(pair);
            }
            return result;
        }
}

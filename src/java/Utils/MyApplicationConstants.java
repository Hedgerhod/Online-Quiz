/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author hieul
 */
public class MyApplicationConstants {
    public class ApplicationScope {
        public static final String HOME_ACTION = "homeAction";
        public static final String LOGIN_ACTION = "loginAction";
        public static final String HOME_PAGE = "homePage";
        public static final String ACCOUNT_MANAGE_ACTION = "accountManageAction";
        public static final String QUESTION_SET_ACTION = "questionSetAction";
        public static final String ADMIN_ACTION = "admin";
        public static final String ERROR_PAGE = "errorPage";
        public static final String JOIN_CLASS_ACTION = "joinClassAction";
        public static final String CLASS_ACTION = "classAction";
        public static final String EXAM_ACTION = "examAction";
        public static final String LOGOUT_ACTION = "logoutAction";
        
    }
    public class ErrorHandle{
        public static final String ACCOUNT_FEATURE_CONSTRAINT_ERROR_PAGE = "roleFeatureAuthorizationErrorPage";
    }
    public class LoginFeature {
        public static final String LOGIN_PAGE = "loginPage";
    }
    public class RegisterFeature {
        //    public static final String ERROR_PAGE = "registerErrorPage";
        public static final String REGISTER_ACTION = "registerAction";
        public static final String REGISTER_PAGE = "registerPage";
    }
    public class AuthorizationFeature {
    }
    public class QuestionSetFeature {
        public static final String VIEW_SET_ACTION = "viewQuestionSetAction";
        public static final String REVIEW_SET_ACTION = "reviewQuestionSetAction";
        public static final String EDIT_SET_ACTION = "editQuestionSetAction";
        public static final String CREATE_SET_ACTION = "createQuestionSetAction";
        public static final String EDIT_SET_PAGE = "editQuestionSetPage";
        public static final String FLASH_CARD_PAGE = "flashcardPage";
//        public static final String QUESTION_SET_PAGE = "questionSetPage";
    }
    public class StudentClassFeature {
        public static final String STUDENT_SCORE_LIST_PAGE = "studentScoreListPage";
    }
    public class ClassFeature {
        public static final String CLASS_DETAIL_PAGE = "classDetailPage";
        public static final String CLASS_DETAIL_ACTION = "classDetailAction";
        public static final String CLASS_JOIN_LIST_PAGE = "classJoinListPage";
        public static final String CLASS_JOIN_LIST_ACION = "classJoinListAction";
        public static final String CLASS_LIST_PAGE = "classListPage";
        public static final String CLASS_LIST_ACION = "classListAction";
        public static final String CLASS_STUDENT_LIST_PAGE = "classStudentListPage";
    }
    public class ClassExamFeature {
        public static final String CLASS_EXAM_LIST_PAGE = "classExamListPage";
        public static final String CLASS_EXAM_DETAIL_PAGE = "classExamDetailPage";
        public static final String CLASS_EXAM_ATTEMPT_ACTION = "attemptExamAction";
    }
    public class TeacherClassFeature {
        //    public static final String JOIN_CLASS_ACTION = "joinClassAction";
        public static final String EDIT_CLASS_QS_PAGE = "editClassQSPage";
        public static final String EDIT_CLASS_QS_ACTION = "editClassQSAction";
        public static final String EDIT_CLASS_ACTION = "editClassAction";
        public static final String CREATE_CLASS_ACTION = "createClassAction";
        public static final String TEACHER_SCORE_LIST_PAGE = "teacherScoreListPage";
    }
    public class AdminFeature {
        public static final String ADD_USER_ACTION = "adminAddUserAction";
        public static final String ADD_SETTING_ACTION = "adminAddSetting";
        public static final String ADMIN_SETTING_ACTION = "adminSettingAction";
        public static final String ADMIN_UPDATE_SETTING_PAGE = "adminUpadteSettingPage";
        public static final String ADMIN_SETTING_LIST_PAGE = "adminSettingListPage";
        public static final String ADMIN_MANAGE_USER_ACTION = "adminManageUser";
        public static final String ADMIN_MANAGER_PAGE = "adminManagerPage";
    }
    public class UpdateAccountFeature {
        public static final String CHANGE_PASS_PAGE = "changePassPage";
        public static final String USER_PROFILE_PAGE = "userProfilePage";
        public static final String USER_PROFILE_ACTION = "userProfileAction";
        public static final String USER_LIST_PAGE = "userListPage";
    }
    public class DeleteAccountFeature {
        public static final String ERROR_PAGE = "errorPage";
    }

    public class StudentExamFeature {
        public static final String TAKE_EXAM_ACTION = "takeExamAction";
        public static final String REVIEW_EXAM_ACTION = "reviewExamAction";
    }
    public class TeacherExamFeature {
        public static final String EDIT_EXAM_ACTION = "editExamAction";
        public static final String MANAGE_EXAM_ACTION = "manageExamAction";
        public static final String CREATE_EXAM_ACTION = "createExamAction";
        public static final String EDIT_EXAM_PAGE = "editExamPage";
    }
}

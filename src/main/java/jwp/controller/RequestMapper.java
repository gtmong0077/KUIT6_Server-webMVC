package jwp.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String,Controller> controllerMap=new HashMap<>();

    public RequestMapper() {
        initMapping();
    }

    private void initMapping() {
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/list", new ListUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/update", new UserUpdateController());
        controllerMap.put("/user/updateForm", new UserUpdateFormController());
        controllerMap.put("/", new HomeController());
    }

    public Controller getController(String path) {
        return controllerMap.get(path);
    }
}

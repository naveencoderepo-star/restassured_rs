import java.util.List;

public class Courses {


    private List<ApiAutomation> api;
    private List<MobileAutomation> mobile;
    private List<WebAutomation> webAutomation;


    public List<ApiAutomation> getApi() {
        return api;
    }

    public void setApi(List<ApiAutomation> api) {
        this.api = api;
    }

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<MobileAutomation> getMobile() {
        return mobile;
    }

    public void setMobile(List<MobileAutomation> mobile) {
        this.mobile = mobile;
    }


}

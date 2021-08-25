package pl.com.chrzanowski.scaffolding.api;

import pl.com.chrzanowski.scaffolding.api.courseplatform.PurchasedCourseGetResponse;

import java.util.List;

public class PurchasedCoursesAdminRequestGetResponse {
    List<PurchasedCourseGetResponse> purchasedCourses;

    public PurchasedCoursesAdminRequestGetResponse(List<PurchasedCourseGetResponse> purchasedCourses) {
        this.purchasedCourses = purchasedCourses;
    }

    public List<PurchasedCourseGetResponse> getPurchasedCourses() {
        return purchasedCourses;
    }
}

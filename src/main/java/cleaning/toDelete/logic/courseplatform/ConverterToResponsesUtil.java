package cleaning.toDelete.logic.courseplatform;

import cleaning.toDelete.api.courseplatform.*;
import cleaning.toDelete.domain.courseplatform.*;

import java.util.ArrayList;
import java.util.List;

public class ConverterToResponsesUtil {

    public static List<MemeGetResponse> memesToResponses(List<MemeData> memes) {
        List<MemeGetResponse> list = new ArrayList<>();

        for (MemeData meme : memes) {
            list.add(new MemeGetResponse(meme));
        }

        return list;
    }

    public static List<CourseCommentGetResponseAdmin> courseCommentsToResponsesAdmin(List<CourseCommentData> courseComments) {
        List<CourseCommentGetResponseAdmin> list = new ArrayList<>();

        for (CourseCommentData courseComment : courseComments) {
            list.add(new CourseCommentGetResponseAdmin(courseComment));
        }

        return list;
    }

    public static List<LessonCommentGetResponseAdmin> lessonCommentsToResponsesAdmin(List<LessonCommentData> lessonComments) {
        List<LessonCommentGetResponseAdmin> list = new ArrayList<>();

        for (LessonCommentData lessonComment : lessonComments) {
            list.add(new LessonCommentGetResponseAdmin(lessonComment));
        }

        return list;
    }

    public static List<CourseCommentGetResponsePublic> courseCommentsToResponsesPublic(List<CourseCommentData> courseComments) {
        List<CourseCommentGetResponsePublic> list = new ArrayList<>();

        for (CourseCommentData courseComment : courseComments) {
            list.add(new CourseCommentGetResponsePublic(courseComment));
        }

        return list;
    }

    public static List<LessonCommentGetResponsePublic> lessonCommentsToResponsesPublic(List<LessonCommentData> lessonComments) {
        List<LessonCommentGetResponsePublic> list = new ArrayList<>();

        for (LessonCommentData lessonComment : lessonComments) {
            list.add(new LessonCommentGetResponsePublic(lessonComment));
        }

        return list;
    }

    public static List<CourseAttachmentGetResponse> courseAttachmentsToResponses(List<CourseAttachmentData> courseAttachments) {
        List<CourseAttachmentGetResponse> list = new ArrayList<>();

        for (CourseAttachmentData courseAttachment : courseAttachments) {
            list.add(new CourseAttachmentGetResponse(courseAttachment));
        }

        return list;
    }

    public static List<LessonGetResponse> lessonsToResponses(List<LessonData> lessons) {
        List<LessonGetResponse> list = new ArrayList<>();

        for (LessonData lesson : lessons) {
            list.add(new LessonGetResponse(lesson));
        }

        return list;
    }

    public static List<ModuleGetResponse> modulesToResponses(List<ModuleData> modules) {
        List<ModuleGetResponse> list = new ArrayList<>();

        for (ModuleData module : modules) {
            list.add(new ModuleGetResponse(module));
        }

        return list;
    }

    public static List<StatisticCourseCompletionGetResponse> courseCompletionToResponses(List<StatisticCourseCompletionData> courseCompletion) {
        List<StatisticCourseCompletionGetResponse> list = new ArrayList<>();
        for (StatisticCourseCompletionData course : courseCompletion) {
            list.add(new StatisticCourseCompletionGetResponse(course));
        }
        return list;
    }

    public static List<LessonAttachmentGetResponse> lessonAttachmentsToResponsesAdmin(List<LessonAttachmentData> lessonAttachments) {
        List<LessonAttachmentGetResponse> list = new ArrayList<>();

        for (LessonAttachmentData lessonAttachment : lessonAttachments) {
            list.add(new LessonAttachmentGetResponse(lessonAttachment));
        }

        return list;
    }
}

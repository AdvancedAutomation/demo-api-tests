package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTest {

    @Test
    public void testPUTStory() {

        //Given
        String expectedProjectName = "Rest Assured new1";
        Response response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedProjectName + "\"}")
                .post("/projects");
        String projectId = response.jsonPath().getString("id");

        String storyName = "My Story";
        response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + storyName + "\"}")
                .post(String.format("/projects/%s/stories", projectId));
        String storyId = response.jsonPath().getString("id");

        // When
        String expectedNewStoryName= "New Story Name";
        response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedNewStoryName + "\"}")
                .put(String.format("/projects/%s/stories/%s", projectId, storyId));

        Assert.assertEquals(response.jsonPath().getString("name"), expectedNewStoryName);
    }
}

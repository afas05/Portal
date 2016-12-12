package servlets;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.groups.responses.GetBannedResponse;

/**
 * Created by Игорь on 04.11.2016.
 */
public class VkApiServlet {

    private VkApiClient apiClient;
    private UserActor actor;
    private int appId = 17508531;
    private String token = "8960b3e73a8120ff0258bb17569a191d14aa8b972f3fafdf5c62cddd06fd615635eb228a1dfe5271e3c05";

    public VkApiServlet() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        apiClient = new VkApiClient(transportClient);
        actor = new UserActor(appId, token);
    }

    public String getFirst(String vk) {

        String firstName = "";

        try {
            firstName = apiClient.users().get().userIds(vk).execute().get(0).getFirstName();

        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return firstName;
    }

    public String getSecond(String vk) {

        String secName = "";

        try {
            secName = apiClient.users().get().userIds(vk).execute().get(0).getLastName();

        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return secName;
    }

    public String razban(String vk) throws ApiException, ClientException {
        int id = getId(vk);
        OkResponse res = apiClient.groups().unbanUser(actor, 115050558, id).execute();
        String r = res.toString();
        return r;
    }

    public int getId(String vk) throws ApiException, ClientException{
        int id = apiClient.users().get().userIds(vk).execute().get(0).getId();
        return id;
    }

}
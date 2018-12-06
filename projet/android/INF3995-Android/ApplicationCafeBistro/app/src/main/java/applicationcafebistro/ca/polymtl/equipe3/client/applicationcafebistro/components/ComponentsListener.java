package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components;

import org.json.JSONObject;

/**
 * this interface is used to share each function we need in a component
 */
public interface ComponentsListener {

    /**
     * this method is call when we get a response of the server. We can use this method to
     * update the component with the new json
     * @param json
     */
    void update(JSONObject json);
}

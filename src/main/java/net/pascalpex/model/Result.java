package net.pascalpex.model;

import java.util.List;
import java.util.Map;

/**
 * The Result class is a data class used for sending the calculated times to the server in the correct format
 */
public class Result {

    private List<Map<String, Object>> result;

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}

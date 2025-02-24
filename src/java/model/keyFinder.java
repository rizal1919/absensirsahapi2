/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class keyFinder implements ContentHandler {
    
    private Object value;
    private boolean found = false;
    private boolean end = false;
    private String key;
    private String matchKey;

    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey;
    }

    public Object getValue() {
        return value;
    }

    public boolean isEnd() {
        return end;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public void startJSON() throws ParseException, IOException {
        found = false;
        end = false;
    }

    public void endJSON() throws ParseException, IOException {
        end = true;
    }

    public boolean primitive(Object value) throws ParseException, IOException {
        if (key != null) {
            if (key.equals(matchKey)) {
                found = true;
                this.value = value;
                key = null;
                return false;
            }
        }
        return true;
    }

   
    public boolean startArray() throws ParseException, IOException {
        return true;
    }

  
    public boolean startObject() throws ParseException, IOException {
        return true;
    }

 
    public boolean startObjectEntry(String key) throws ParseException, IOException {
        this.key = key;
        return true;
    }

   
    public boolean endArray() throws ParseException, IOException {
        return false;
    }

 
    public boolean endObject() throws ParseException, IOException {
        return true;
    }


    public boolean endObjectEntry() throws ParseException, IOException {
        return true;
    }
    
}

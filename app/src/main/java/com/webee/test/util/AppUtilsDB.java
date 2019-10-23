package com.webee.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUtilsDB {

    public static String getQuery(String tabla, HashMap<String, String> wheres,
                                  String ordenCampo, String ordenTipo) {
        return getQuery(tabla, wheres, ordenCampo, ordenTipo, 0, 0);
    }
    public static String getQuery(String tabla, HashMap<String, String> wheres,
                                  String ordenCampo, String ordenTipo, int numPagina, int tnoPagina){

        String query = "SELECT * FROM " + tabla +" where 1=1";
        int c = 0;
        if(wheres!=null){
            for (Map.Entry<String, String> entry : wheres.entrySet()){
                c++;
                String[] arrKeys = entry.getKey().split(" ");
                if(entry.getValue() == null ){
                    query += " and " + entry.getKey() + "";
                }
                else if(arrKeys.length==2){
                    query += " and " + arrKeys[0] + " "+arrKeys[1]+"?";
                }else {
                    query += " and " + entry.getKey() + "=?";
                }
            }
        }

        if(ordenCampo!=null && !ordenCampo.isEmpty()){
            query+=" order by "+ordenCampo;
        }

        if(ordenTipo!=null && !ordenTipo.isEmpty()){
            query+=" "+ordenTipo;
        }


        if(tnoPagina != 0) {
            int offset = (numPagina-1)*tnoPagina;
            query += " LIMIT "+tnoPagina+" OFFSET " + offset;
        }
        return query;
    }

    public static String[] getSelectionArgs(HashMap<String, String> wheres){

        List<String> list = new ArrayList<String>();

        if(wheres!=null){
            for (Map.Entry<String, String> entry : wheres.entrySet()){
                if(entry.getValue() != null) {
                    list.add(entry.getValue());

                }
            }
        }


        String[] selectionArgs = list.toArray(new String[0]);
        return selectionArgs;

    }
}

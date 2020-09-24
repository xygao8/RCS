package com.iflytek.rcs.utils;


import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.StringTokenizer;

/**
 * xml和javabean转换公具类
 */
public class XmlUtil {
    @SuppressWarnings({"unchecked"})
    public static <T> T xml2bean(String xml, Class<T> clazz) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(in, getEncoding(xml)));
            t = (T) unmarshaller
                    .unmarshal(buffer);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String bean2xml(Object obj, String charSet) {
        String result = "";
        try {
            JAXBContextImpl context = (JAXBContextImpl) JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charSet);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
                @Override
                public String getPreferredPrefix(String s, String s1, boolean b) {
                    if (s.equals("urn:oma:xml:rest:netapi:messaging:1")) return "msg";
                    return "";
                }
            });
            StringWriter sw = new StringWriter();
            sw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            marshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getEncoding(String text) {
        String result = null;

        String xml = text.trim();

        if (xml.startsWith("<?xml")) {
            int end = xml.indexOf("?>");
            String sub = xml.substring(0, end);
            StringTokenizer tokens = new StringTokenizer(sub, " =\"\'");

            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();

                if ("encoding".equals(token)) {
                    if (tokens.hasMoreTokens()) {
                        result = tokens.nextToken();
                    }

                    break;
                }
            }
        }

        return result;
    }
}

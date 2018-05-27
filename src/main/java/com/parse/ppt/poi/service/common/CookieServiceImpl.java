package com.parse.ppt.poi.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * CookieServier，使用的是自己写的EasyCookie代码
 *
 * @author Jupiter
 * @version 2018/05/26 11:31
 */
@Service
public class CookieServiceImpl implements CookieService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public <T> boolean addCookie(T user, HttpServletResponse response) {
        logger.info("------->  start!  user = {}", user);
        try {
            Class<?> entityClass = user.getClass();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String variableName = field.getName();
                String variableValue = String.valueOf(field.get(user));
                Cookie cookie = new Cookie(variableName, variableValue);
                //  下面这一行代码    【Super！！！！】  重要 ！具体为什么，一起探讨吧
                cookie.setPath("/");
                // 单位为秒
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
            logger.info("------->  end!   SUCCESS!");
            return true;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public <T> boolean removeCookie(Class<T> userClass, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start! ");
        try {
            Map<String, Cookie> cookieMap = getCookieMap(request);
            if (cookieMap == null || cookieMap.size() <= 0) {
                return false;
            }
            Field[] fields = userClass.getDeclaredFields();
            for (Field variable : fields) {
                String variableName = variable.getName();
                if (cookieMap.containsKey(variableName)) {
                    Cookie cookie = cookieMap.get(variableName);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            logger.info("------->  end!   SUCCESS!");
            return true;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public <T> T loadCookie(Class<T> userClass, HttpServletRequest request) {
        logger.info("------->  start! ");
        try {
            T result = userClass.getDeclaredConstructor().newInstance();
            Map<String, Cookie> cookieMap = getCookieMap(request);
            Field[] fields = userClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, userClass);
                Method writeMethod = pd.getWriteMethod();
                // 如果是 Primitive 数据类型
                boolean isPrimitive = field.getType().isPrimitive();
                if (cookieMap.containsKey(fieldName)) {
                    String theCookieValue = cookieMap.get(fieldName).getValue();
                    String fieldPrimitiveType = null;
                    if (isPrimitive) {
                        fieldPrimitiveType = field.getType().getName();
                    } else {
                        fieldPrimitiveType = String.valueOf(field.getType().getDeclaredField("TYPE").get(new Object()));
                    }
                    if ("bool".equals(fieldPrimitiveType)) {
                        boolean isCorrectValue = "true".equalsIgnoreCase(theCookieValue.trim()) || "false".equalsIgnoreCase(theCookieValue.trim());
                        if (!isCorrectValue) {
                            throw new IllegalArgumentException("The cookie name = [" + fieldName + "]," +
                                    "value = [" + theCookieValue + "]，and the field primitive type is [" + fieldPrimitiveType + "] !");
                        }
                        writeMethod.invoke(result, Boolean.parseBoolean(theCookieValue));
                    } else if ("char".equals(fieldPrimitiveType)) {
                        // 如果 theCookieValue长度大于1，则不合法
                        if (theCookieValue.trim().length() > 1) {
                            throw new IllegalArgumentException("The cookie name = [" + fieldName + "]," +
                                    "value = [" + theCookieValue + "]，and the field primitive type is [" + fieldPrimitiveType + "] !");
                        }
                        writeMethod.invoke(result, theCookieValue.charAt(0));
                    } else if ("byte".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Byte.parseByte(theCookieValue));
                    } else if ("short".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Short.parseShort(theCookieValue));
                    } else if ("int".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Integer.parseInt(theCookieValue));
                    } else if ("long".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Long.parseLong(theCookieValue));
                    } else if ("float".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Float.parseFloat(theCookieValue));
                    } else if ("double".equals(fieldPrimitiveType)) {
                        writeMethod.invoke(result, Double.parseDouble(theCookieValue));
                    } else if ("void".equals(fieldPrimitiveType)) {
                        throw new IllegalArgumentException("The field type is void ! Which is wrong!");
                    } else {
                        throw new IllegalArgumentException("Unknown field type !");
                    }
                }
            }
            logger.info("------->  start!   result = {}", result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 私有的处理方法，把Cookie映射为一个Map对象，方便后续的快速遍历
     *
     * @param request HttpServletRequest
     * @return CookieMap
     */
    private Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!");
        }
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = null;
        if (cookies != null && cookies.length > 0) {
            cookieMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                cookieMap.put(cookieName, cookie);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!    cookieMap = {}", cookieMap);
        }
        return cookieMap;
    }
}

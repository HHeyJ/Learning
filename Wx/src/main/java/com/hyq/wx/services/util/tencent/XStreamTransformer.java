package com.hyq.wx.services.util.tencent;

import com.hyq.wx.services.common.message.WxCpXmlMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.Writer;
import java.util.*;

public class XStreamTransformer {
  private static final Map<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = new HashMap<>();
  private static XStream xStream = null;

  static {
    xStream = new XStream(new XppDriver() {
      public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, this.getNameCoder()) {
          protected String PREFIX_CDATA = "<![CDATA[";
          protected String SUFFIX_CDATA = "]]>";
          protected String PREFIX_MEDIA_ID = "<MediaId>";
          protected String SUFFIX_MEDIA_ID = "</MediaId>";

          protected void writeText(QuickWriter writer, String text) {
            if (text.startsWith(this.PREFIX_CDATA) && text.endsWith(this.SUFFIX_CDATA)) {
              writer.write(text);
            } else if (text.startsWith(this.PREFIX_MEDIA_ID) && text.endsWith(this.SUFFIX_MEDIA_ID)) {
              writer.write(text);
            } else {
              super.writeText(writer, text);
            }

          }

          public String encodeNode(String name) {
            return name;
          }
        };
      }
    });
    xStream.ignoreUnknownElements();
    xStream.setMode(1001);
    xStream.addPermission(NullPermission.NULL);
    xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);

    registerClass(WxCpXmlMessage.class);
  }

  /**
   * xml -> pojo.
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, String xml) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
    return object;
  }

  /**
   * 注册扩展消息的解析器.
   *
   * @param clz     类型
   * @param xStream xml解析器
   */
  public static void register(Class<?> clz, XStream xStream) {
    CLASS_2_XSTREAM_INSTANCE.put(clz, xStream);
  }

  /**
   * 会自动注册该类及其子类.
   *
   * @param clz 要注册的类
   */
  private static void registerClass(Class<?> clz) {

    xStream.processAnnotations(clz);
    xStream.processAnnotations(getInnerClasses(clz));
    register(clz, xStream);
  }

  private static Class<?>[] getInnerClasses(Class<?> clz) {
    Class<?>[] innerClasses = clz.getClasses();

    List<Class<?>> result = new ArrayList<>(Arrays.asList(innerClasses));
    for (Class<?> inner : innerClasses) {
      Class<?>[] innerClz = getInnerClasses(inner);
      result.addAll(Arrays.asList(innerClz));
    }

    return result.toArray(new Class<?>[0]);
  }
}

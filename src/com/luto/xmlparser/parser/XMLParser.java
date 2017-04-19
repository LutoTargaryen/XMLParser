package com.luto.xmlparser.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	// 容器以键值对的形式存储对象
	private Map<String, Object> context = new HashMap<>();

	public XMLParser() {

	}

	public XMLParser(String path) {
		parser(path);
	}

	private void parser(String path) {
		// 获取DocumentBuilderFactory对象
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		// 获取DocumentBuilder对象
		try {
			DocumentBuilder documentBuilder = builderFactory
					.newDocumentBuilder();
			// 获取文档对象
			Document document = documentBuilder.parse(this.getClass()
					.getClassLoader().getResourceAsStream(path));
			// 获取根元素
			Element documentElement = document.getDocumentElement();

			NodeList nodeList = documentElement.getChildNodes();
			// 遍历文档节点
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				// 判断节点类型是否为元素，更多类型参见：http://www.w3school.com.cn/jsref/prop_node_nodetype.asp
				if (Node.ELEMENT_NODE == node.getNodeType()) {
					// 获取id的值
					String Id = node.getAttributes().getNamedItem("id")
							.getNodeValue();
					// 获取class
					String clazz = node.getAttributes().getNamedItem("class")
							.getNodeValue();
					// 通过反射创建class的实例对象
					Object object = Class.forName(clazz).newInstance();
					// 把获取到的对象和id以键值对的形式存储到容器中
					context.put(Id, object);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Object getObject(String beanId) {
		return context.get(beanId);
	}
}

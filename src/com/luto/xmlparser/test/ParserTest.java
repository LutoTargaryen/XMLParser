package com.luto.xmlparser.test;

import org.junit.Test;

import com.luto.xmlparser.dao.BeanDao;
import com.luto.xmlparser.parser.XMLParser;

public class ParserTest {
	@Test
	public void test() {
		XMLParser xmlParser = new XMLParser("bean.xml");
		BeanDao bean = (BeanDao) xmlParser.getObject("bean");
		System.out.println(bean.add());
		System.out.println(bean.say());
	}
}

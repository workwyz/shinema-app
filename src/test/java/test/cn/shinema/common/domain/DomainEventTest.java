package test.cn.shinema.common.domain;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import cn.shinema.core.domain.DomainEvent;

class DomainEventTest {

	@Test
	void test() {

//		Reflections reflections = new Reflections("cn.shinema.web");
		Reflections reflections = new Reflections("");

		Set<Class<? extends DomainEvent>> set = reflections.getSubTypesOf(DomainEvent.class);

		if (null != set) {
			set.forEach(clazz -> {
				System.out.println(clazz.getName());
			});
		}
	}

}

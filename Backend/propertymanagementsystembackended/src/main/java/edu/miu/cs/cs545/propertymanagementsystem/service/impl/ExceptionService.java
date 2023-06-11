package edu.miu.cs.cs545.propertymanagementsystem.service.impl;

import edu.miu.cs.cs545.propertymanagementsystem.aop.ExceptionAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExceptionService {

        private final ExceptionAspect exceptionAspect;

        @Autowired
        public ExceptionService(ExceptionAspect exceptionAspect) {
            this.exceptionAspect=exceptionAspect;
        }

        public void testException() {
                throw new RuntimeException("Exception occurred");
        }
}

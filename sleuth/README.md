#### pom文件
```$xslt
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
			<exclusions>
				<!--禁用监控-->
				<exclusion>
					<artifactId>spring-boot-starter-actuator</artifactId>
					<groupId>org.springframework.boot</groupId>
				</exclusion>
			</exclusions>
		</dependency>
```
如上，排除了监控功能，觉得监控功能意义不大


#### 原理



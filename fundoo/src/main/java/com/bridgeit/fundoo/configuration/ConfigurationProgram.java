package com.bridgeit.fundoo.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgeit.fundoo.dao.INoteDao;
import com.bridgeit.fundoo.dao.IUserDao;
import com.bridgeit.fundoo.dao.NoteDao;
import com.bridgeit.fundoo.dao.UserDao;
import com.bridgeit.fundoo.service.ILabelService;
import com.bridgeit.fundoo.service.INoteService;
import com.bridgeit.fundoo.service.IUserService;
import com.bridgeit.fundoo.service.LabelServiceImplementation;
import com.bridgeit.fundoo.service.NoteServiceImplementation;
import com.bridgeit.fundoo.service.UserServiceImplementation;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bridgeit.fundoo")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ConfigurationProgram {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	   private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	   private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

	   private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	   private static final String PROPERTY_NAME_UPDATE="hibernate.hbm2ddl.auto";

	   private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	   private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	   private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";



	   @Resource
	   private Environment env;


		@Autowired 
		private Environment environment;

	   @Bean

	   public DataSource dataSource() {
		   System.out.println("data source");
		
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();



	       dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	       
	       dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	
	       dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));

	       dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

	  
	       System.out.println(dataSource);

	       return dataSource;

	   }



	  @Bean

	   public LocalSessionFactoryBean sessionFactory() {
		   System.out.println("session factory");

	       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

	       sessionFactoryBean.setDataSource(dataSource());

	       
	       sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(

	PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

	       sessionFactoryBean.setHibernateProperties(hibProperties());

	       return sessionFactoryBean;

	   }



	  private Properties hibProperties() {
		   System.out.println("property");

	       Properties properties = new Properties();

	       	properties.put(PROPERTY_NAME_UPDATE,env.getRequiredProperty("hibernate.hbm2ddl.auto"));
	       	
	       properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));

	       properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	       System.out.println(properties);
	       return properties; 

	   }



	   @Bean

	   public HibernateTransactionManager transactionManager() {
		   System.out.println("transaction");

	       HibernateTransactionManager transactionManager = new HibernateTransactionManager();

	       transactionManager.setSessionFactory(sessionFactory().getObject());

	       return transactionManager;

	   }
	   @Bean
	   public IUserService getIUserService()
	   {
		   System.out.println("service object");
		   return new UserServiceImplementation();
	   }
	   @Bean
	   public IUserDao getIUserDao()
	   {
		   System.out.println("user dao object");
		   return new UserDao();
	   }
	
	   @Bean
	   public String getKey()
	   {
		   return "ramana";
	   }
	   @Bean
	   public ILabelService getILabelService()
	   {
		   return new LabelServiceImplementation();
	   }
	   @Bean
	   public INoteService getINoteService()
	   {
		   return new NoteServiceImplementation();
	   }
	   @Bean
	   public INoteDao getINoteDao()
	   {
		   return new NoteDao();
	   }
//	  
//	   @Bean 
//		public WebMvcConfigurer corsConfigurer()
//		{ 
//		   System.out.println("hihihihihi");
//			return new WebMvcConfigurer() 
//			{
//				@Override public void addCorsMappings(CorsRegistry registry) 
//				{
//							registry.addMapping("/**") .allowedMethods(environment.getProperty("get"),
//							environment.getProperty("post"), environment.getProperty("put"),
//							environment.getProperty("delete")) .allowedOrigins("http://192.168.0.16:4203").allowedHeaders("*").exposedHeaders("jwtTokenxxx").exposedHeaders("token");
//				}
//
//				@Override
//				public void addFormatters(FormatterRegistry registry) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public Validator getValidator() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//
//				@Override
//				public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configurePathMatch(PathMatchConfigurer configurer) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void addInterceptors(InterceptorRegistry registry) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public MessageCodesResolver getMessageCodesResolver() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//
//				@Override
//				public void addViewControllers(ViewControllerRegistry registry) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configureViewResolvers(ViewResolverRegistry registry) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void addResourceHandlers(ResourceHandlerRegistry registry) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//					// TODO Auto-generated method stub
//					
//				}
//			};
//		}
	   
}

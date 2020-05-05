package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;
import by.itacademy.tatabakach.transportcompany.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private ICarDao dao;

	@Override
	public ICar createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICar entity) {
		if (entity.getId() == null) {
			LOGGER.info("new car create: {}", entity);
			dao.insert(entity);

			sendMail(entity.toString(), "new car created");

		} else {
			LOGGER.debug("car update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ICar get(final Integer id) {
		final ICar entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all cars");
		dao.deleteAll();
	}

	@Override
	public List<ICar> getAll() {
		final List<ICar> all = dao.selectAll();
		return all;
	}

	@Override
	public List<ICar> find(CarFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CarFilter filter) {
		return dao.getCount(filter);
	}

	private void sendMail(String body, String subject) {

		final String username = "tata.bakach@gmail.com";
		final String password = "qhirsytipessxwuy";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dmitri.zhyvushko@gmail.com"));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ICar> search(String string) {
		return dao.search(string);
	}

}

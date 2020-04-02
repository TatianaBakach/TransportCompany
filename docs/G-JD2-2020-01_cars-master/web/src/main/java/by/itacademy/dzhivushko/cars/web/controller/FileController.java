package by.itacademy.dzhivushko.cars.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	public static final String FILE_FOLDER = "d:\\";

	@RequestMapping(method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") final MultipartFile file,
			final RedirectAttributes redirectAttributes) throws IOException, GeneralSecurityException {
		String originalFilename = file.getOriginalFilename(); // to DB
		String uuid = UUID.randomUUID().toString(); // to DB

		LOGGER.info("Uploaded file: {}. UUID={}", originalFilename, uuid);

		InputStream inputStream = file.getInputStream();
		Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(), StandardCopyOption.REPLACE_EXISTING);
		return "redirect:/";
	}

	@RequestMapping(value = "/image")
	public @ResponseBody byte[] getImage(@RequestParam(name = "uuid", required = false) String uuid)
			throws IOException {
		if (StringUtils.isNotBlank(uuid)) {
			byte[] byteArray = readFileToByteArray(new File(FILE_FOLDER + uuid));
			return byteArray;
		}
		return null;
	}

	private static byte[] readFileToByteArray(File file) {
		byte[] bArray = new byte[(int) file.length()];
		try (FileInputStream fis = new FileInputStream(file);) {

			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}
}

package br.com.doacao.webapp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Gabriela Santos
 */
@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
	
    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
    	return (zonedDateTime == null ? null : Timestamp.from(zonedDateTime.toInstant()));
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
    	return (timestamp == null ? null : ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault()));
    }
}

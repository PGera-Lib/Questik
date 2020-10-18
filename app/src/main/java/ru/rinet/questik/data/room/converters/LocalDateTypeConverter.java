package ru.rinet.questik.data.room.converters;

import org.joda.time.LocalDate;

import androidx.room.TypeConverter;



public class LocalDateTypeConverter {

	@TypeConverter
	public static LocalDate toDate(final String value) {
		return LocalDate.parse(value);
	}

	@TypeConverter
	public static String toString(final LocalDate value) {
		return value.toString("dd.MM.yyyy-HH:mm");
	}

}
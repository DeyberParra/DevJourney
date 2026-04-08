package com.app.core.di

import javax.inject.Qualifier

/**
 * @author : DeyberParra
 * @description : Creation of Qualifiers to better distinguish possible Retrofit configurations if there is more than one endpoint
 * */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountriesRetrofit
package com.snowplowanalytics.snowplow.loader.redshift

object RedshiftEventsTable {
  def statement(tableName: String): String =
    s"""
       |CREATE TABLE IF NOT EXISTS $tableName (
       |	app_id varchar(255) encode ZSTD,
       |	platform varchar(255) encode ZSTD,
       |	etl_tstamp timestamp  encode ZSTD,
       |	collector_tstamp timestamp not null encode RAW,
       |	dvce_created_tstamp timestamp encode ZSTD,
       |	event varchar(128) encode ZSTD,
       |	event_id char(36) not null unique encode ZSTD,
       |	txn_id int encode ZSTD,
       |	name_tracker varchar(128) encode ZSTD,
       |	v_tracker varchar(100) encode ZSTD,
       |	v_collector varchar(100) encode ZSTD not null,
       |	v_etl varchar(100) encode ZSTD not null,
       |	user_id varchar(255) encode ZSTD,
       |	user_ipaddress varchar(128) encode ZSTD,
       |	user_fingerprint varchar(128) encode ZSTD,
       |	domain_userid varchar(128) encode ZSTD,
       |	domain_sessionidx int encode ZSTD,
       |	network_userid varchar(128) encode ZSTD,
       |	geo_country char(2) encode ZSTD,
       |	geo_region char(3) encode ZSTD,
       |	geo_city varchar(75) encode ZSTD,
       |	geo_zipcode varchar(15) encode ZSTD,
       |	geo_latitude double precision encode ZSTD,
       |	geo_longitude double precision encode ZSTD,
       |	geo_region_name varchar(100) encode ZSTD,
       |	ip_isp varchar(100) encode ZSTD,
       |	ip_organization varchar(128) encode ZSTD,
       |	ip_domain varchar(128) encode ZSTD,
       |	ip_netspeed varchar(100) encode ZSTD,
       |	page_url varchar(4096) encode ZSTD,
       |	page_title varchar(2000) encode ZSTD,
       |	page_referrer varchar(4096) encode ZSTD,
       |	page_urlscheme varchar(16) encode ZSTD,
       |	page_urlhost varchar(255) encode ZSTD,
       |	page_urlport int encode ZSTD,
       |	page_urlpath varchar(3000) encode ZSTD,
       |	page_urlquery varchar(6000) encode ZSTD,
       |	page_urlfragment varchar(3000) encode ZSTD,
       |	refr_urlscheme varchar(16) encode ZSTD,
       |	refr_urlhost varchar(255) encode ZSTD,
       |	refr_urlport int encode ZSTD,
       |	refr_urlpath varchar(6000) encode ZSTD,
       |	refr_urlquery varchar(6000) encode ZSTD,
       |	refr_urlfragment varchar(3000) encode ZSTD,
       |	refr_medium varchar(25) encode ZSTD,
       |	refr_source varchar(50) encode ZSTD,
       |	refr_term varchar(255) encode ZSTD,
       |	mkt_medium varchar(255) encode ZSTD,
       |	mkt_source varchar(255) encode ZSTD,
       |	mkt_term varchar(255) encode ZSTD,
       |	mkt_content varchar(500) encode ZSTD,
       |	mkt_campaign varchar(255) encode ZSTD,
       |	se_category varchar(1000) encode ZSTD,
       |	se_action varchar(1000) encode ZSTD,
       |	se_label varchar(4096) encode ZSTD,
       |	se_property varchar(1000) encode ZSTD,
       |	se_value double precision encode ZSTD,
       |	tr_orderid varchar(255) encode ZSTD,
       |	tr_affiliation varchar(255) encode ZSTD,
       |	tr_total dec(18,2) encode ZSTD,
       |	tr_tax dec(18,2) encode ZSTD,
       |	tr_shipping dec(18,2) encode ZSTD,
       |	tr_city varchar(255) encode ZSTD,
       |	tr_state varchar(255) encode ZSTD,
       |	tr_country varchar(255) encode ZSTD,
       |	ti_orderid varchar(255) encode ZSTD,
       |	ti_sku varchar(255) encode ZSTD,
       |	ti_name varchar(255) encode ZSTD,
       |	ti_category varchar(255) encode ZSTD,
       |	ti_price dec(18,2) encode ZSTD,
       |	ti_quantity int encode ZSTD,
       |	pp_xoffset_min int encode ZSTD,
       |	pp_xoffset_max int encode ZSTD,
       |	pp_yoffset_min int encode ZSTD,
       |	pp_yoffset_max int encode ZSTD,
       |	useragent varchar(1000) encode ZSTD,
       |	br_name varchar(50) encode ZSTD,
       |	br_family varchar(50) encode ZSTD,
       |	br_version varchar(50) encode ZSTD,
       |	br_type varchar(50) encode ZSTD,
       |	br_renderengine varchar(50) encode ZSTD,
       |	br_lang varchar(255) encode ZSTD,
       |	br_features_pdf boolean encode ZSTD,
       |	br_features_flash boolean encode ZSTD,
       |	br_features_java boolean encode ZSTD,
       |	br_features_director boolean encode ZSTD,
       |	br_features_quicktime boolean encode ZSTD,
       |	br_features_realplayer boolean encode ZSTD,
       |	br_features_windowsmedia boolean encode ZSTD,
       |	br_features_gears boolean encode ZSTD,
       |	br_features_silverlight boolean encode ZSTD,
       |	br_cookies boolean encode ZSTD,
       |	br_colordepth varchar(12) encode ZSTD,
       |	br_viewwidth int encode ZSTD,
       |	br_viewheight int encode ZSTD,
       |	os_name varchar(50) encode ZSTD,
       |	os_family varchar(50)  encode ZSTD,
       |	os_manufacturer varchar(50)  encode ZSTD,
       |	os_timezone varchar(255)  encode ZSTD,
       |	dvce_type varchar(50)  encode ZSTD,
       |	dvce_ismobile boolean encode ZSTD,
       |	dvce_screenwidth int encode ZSTD,
       |	dvce_screenheight int encode ZSTD,
       |	doc_charset varchar(128) encode ZSTD,
       |	doc_width int encode ZSTD,
       |	doc_height int encode ZSTD,
       |	tr_currency char(3) encode ZSTD,
       |	tr_total_base dec(18, 2) encode ZSTD,
       |	tr_tax_base dec(18, 2) encode ZSTD,
       |	tr_shipping_base dec(18, 2) encode ZSTD,
       |	ti_currency char(3) encode ZSTD,
       |	ti_price_base dec(18, 2) encode ZSTD,
       |	base_currency char(3) encode ZSTD,
       |	geo_timezone varchar(64) encode ZSTD,
       |	mkt_clickid varchar(128) encode ZSTD,
       |	mkt_network varchar(64) encode ZSTD,
       |	etl_tags varchar(500) encode ZSTD,
       |	dvce_sent_tstamp timestamp encode ZSTD,
       |	refr_domain_userid varchar(128) encode ZSTD,
       |	refr_dvce_tstamp timestamp encode ZSTD,
       |	domain_sessionid char(128) encode ZSTD,
       |	derived_tstamp timestamp encode ZSTD,
       |	event_vendor varchar(1000) encode ZSTD,
       |	event_name varchar(1000) encode ZSTD,
       |	event_format varchar(128) encode ZSTD,
       |	event_version varchar(128) encode ZSTD,
       |	event_fingerprint varchar(128) encode ZSTD,
       |	true_tstamp timestamp encode ZSTD,
       |        load_tstamp timestamp default getdate() null encode AZ64,
       |	CONSTRAINT event_id_0110_pk PRIMARY KEY(event_id)
       |)
       |DISTSTYLE KEY
       |DISTKEY (event_id)
       |SORTKEY (collector_tstamp);
      |""".stripMargin
}

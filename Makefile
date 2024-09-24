build:
	mvn clean install

build-image:
	docker build -t monolith-bank-app-image .

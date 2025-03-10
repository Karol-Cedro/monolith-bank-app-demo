build:
	mvn clean install

build-image:
	docker build -t monolith-bank-app-image .

deploy-on-minikube:
	minikube image load monolith-bank-app-image:latest && \
	kubectl apply -f deployment/

clear-ports:
	sudo lsof -i :9000 -t | xargs sudo kill -9

forward-ports:
	kubectl port-forward svc/monolith-bank-app-service 9000:9000

delete-from-minikube:
	kubectl delete -f deployment/

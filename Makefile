
build:
	mvn complile

package:
	mvn package

docker-build:
	docker build -t ms-logistica:1.0 .
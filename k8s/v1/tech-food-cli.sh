#!/bin/bash

set -e

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo "🚀 Setup Tech Food Kubernetes"

# Verificar se minikube está rodando
if ! kubectl get nodes &> /dev/null; then
    echo "❌ Minikube não está rodando. Execute: minikube start"
    exit 1
fi

# Criar imagem docker
echo "🔨 Criando imagem docker..."
cd ../../  # Volta para a raiz onde está o Dockerfile
docker build --no-cache -t techfood:v2 .

# Copiar imagem para o minikube
echo "📦 Copiando imagem para o minikube..."
minikube image load techfood:v2

# Volta para a pasta dos manifestos
cd k8s/v1

# Verificar/ativar metrics-server
echo "📊 Verificando metrics-server..."
minikube addons enable metrics-server > /dev/null 2>&1

# Descobrir IP local
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
    LOCAL_IP=$(hostname -I | awk '{print $1}')
elif [[ "$OSTYPE" == "darwin"* ]]; then
    LOCAL_IP=$(ifconfig | grep "inet " | grep -v 127.0.0.1 | awk '{print $2}' | head -n1)
else
    read -p "Digite o IP local da sua máquina: " LOCAL_IP
fi

echo "📍 IP local: $LOCAL_IP"

# Atualizar configmap com o IP
sed -i "s/CHANGE_ME_TO_YOUR_LOCAL_IP/$LOCAL_IP/g" tech-food-configmap-v1.yaml

# Aplicar manifestos
echo "⚙️ Aplicando recursos..."
kubectl apply -f tech-food-configmap-v1.yaml
kubectl apply -f tech-food-db-secret-v1.yaml
kubectl apply -f tech-food-deployment-v1.yaml
kubectl apply -f tech-food-service-v1.yaml
kubectl apply -f tech-food-hpa-v1.yaml

echo -e "\n${YELLOW}⏳ Aguardando pods...${NC}"
kubectl wait --for=condition=ready pod -l app=techfood --timeout=180s

MINIKUBE_IP=$(minikube ip)

echo -e "\n${GREEN}🎉 Setup concluído!${NC}"
echo -e "\n${BLUE}🌐 Acesse sua aplicação:${NC}"
echo -e "  URL: http://$MINIKUBE_IP:30000/swagger-ui/index.html"
echo -e "  Health: http://$MINIKUBE_IP:30000/actuator/health"

echo -e "\n${BLUE}📝 Comandos úteis:${NC}"
echo -e "  kubectl get pods"
echo -e "  kubectl logs -f deployment/tech-food-deployment-v1"
echo -e "  kubectl get hpa"
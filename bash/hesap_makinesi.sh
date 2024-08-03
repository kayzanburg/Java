#!/bin/bash

echo "Hesaplama operatorunu girin"
echo "Secenekler:"
echo "+ (toplama)"
echo "- (cikarma)"
echo "* (carpma)"
echo "/ (bolme)"
echo "% (modul)"
echo "! (faktoriyel)"
read operand

if [ "$operand" == "!" ]; then
    echo "Bir sayi girin"
    read num1
    result=1
    for (( i=1; i<=num1; i++ ))
    do
        result=$((result * i))
    done
    echo "Sonuc : $result"
else
    echo "Birinci sayiyi girin"
    read num1
    echo "Ikinci sayiyi girin"
    read num2

    case $operand in
        +)
            result=$((num1 + num2))
            ;;
        -)
            result=$((num1 - num2))
            ;;
        *)
            result=$((num1 * num2))
            ;;
        /)
            result=$((num1 / num2))
            ;;
        %)
            result=$((num1 % num2))
            ;;
        *)
            echo "Gecersiz operator"
            exit 1
            ;;
    esac
    echo "Sonuc : $result"
fi

def integrate(function, a, b, i):
    dx = (b - a) / i
    integr = 0
    for x in range(i):
        x = x * dx + a
        integr += dx * eval(function)
    return integr
 
def main(args):
    function = input("Funkcja: ")
    a = float(input("Początek przedziału: "))
    b = float(input("Koniec przedziału: "))
    i = int(input("Liczba podprzedziałów: "))
    print("Całka = {integrate}".format(integrate = integrate(function, a, b, i)))
    return 0
 
if __name__ == '__main__':
    import sys
    sys.exit(main(sys.argv))

import tensorflow as tf
device_name = tf.test.gpu_device_name()
if device_name != '/device:GPU:0':
	raise SystemError('GPU device not found')
print('Found GPU at: {}'.format(device_name))

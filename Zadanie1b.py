def simpson_integration_modified(my_func, a, b, n):

  delta_x = (b-a)/n
  total = my_func(a) + my_func(b)
  subtotal_sum_1 = 0
  subtotal_sum_2 = 0

  for i in range(0, n, 2):
    x = a + i * delta_x
    subtotal_sum_1 += my_func(x)

  for i in range(1, n-1, 2):
    x = a + i * delta_x
    subtotal_sum_2 += my_func(x)
  return delta_x * (total + 4 * subtotal_sum_1 + 2 * subtotal_sum_2) / 3

integral = simpson_integration_modified(lambda x: x**2, 0.0, 1.0, 100)
print(integral)

import tensorflow as tf
device_name = tf.test.gpu_device_name()
if device_name != '/device:GPU:0':
	raise SystemError('GPU device not found')
print('Found GPU at: {}'.format(device_name))

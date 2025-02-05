from collections.abc import Callable
from math import log
from math import exp
import matplotlib.pyplot as plt
import numpy as np


def main():
	# task2()
	# task3()
	# task4()
	# task5()
	# task7()
	task8()


def task2():
	# y' + y + e^-x = 0
	# y(0) = 1
	# x = 0..4
	# y(x) = (1-x)e^-x

	# y' = z = -y - e^-x = f
	# y(0) = 1

	dx = 0.01
	x_max = 4
	f = lambda _x, _y: -_y[0] - exp(-_x)
	exact = lambda _x: (1-_x) * exp(-_x)

	# Euler
	plot_data_rk1 = []
	cx, y = 0, 1
	plot_data_rk1.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk1([f], cx, [y], dx)
		cx += dx
		plot_data_rk1.append((cx, y, y - exact(cx)))
	plot_data_rk1 = np.array(plot_data_rk1)

	# RK2
	plot_data_rk2 = []
	cx, y = 0, 1
	plot_data_rk2.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk2([f], cx, [y], dx)
		cx += dx
		plot_data_rk2.append((cx, y, y - exact(cx)))
	plot_data_rk2 = np.array(plot_data_rk2)

	# RK4
	plot_data_rk4 = []
	cx, y = 0, 1
	plot_data_rk4.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk4([f], cx, [y], dx)
		cx += dx
		plot_data_rk4.append((cx, y, y - exact(cx)))
	plot_data_rk4 = np.array(plot_data_rk4)

	plot_data('task2', plot_data_rk1, plot_data_rk2, plot_data_rk4)


def task3():
	# y' + 2x3 - 12x2 + 20x -8.5 = 0
	# y(0) = 1
	# x = 0..4
	# y(x) = −1/2x4 + 4x3 − 10x2 + 8.5x + 1

	# y' = -2x3 + 12x2 -20x + 8.5
	# y(0) = 1

	dx = 0.01
	x_max = 4
	f = lambda _x, _y: -2 * _x ** 3 + 12 * _x ** 2 - 20 * _x + 8.5
	exact = lambda _x: -.5 * _x ** 4 + 4 * _x ** 3 - 10 * _x ** 2 + 8.5 * _x + 1

	# Euler
	plot_data_rk1 = []
	cx, y = 0, 1
	plot_data_rk1.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk1([f], cx, [y], dx)
		cx += dx
		plot_data_rk1.append((cx, y, y - exact(cx)))
	plot_data_rk1 = np.array(plot_data_rk1)

	# RK2
	plot_data_rk2 = []
	cx, y = 0, 1
	plot_data_rk2.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk2([f], cx, [y], dx)
		cx += dx
		plot_data_rk2.append((cx, y, y - exact(cx)))
	plot_data_rk2 = np.array(plot_data_rk2)

	# RK4
	plot_data_rk4 = []
	cx, y = 0, 1
	plot_data_rk4.append((cx, y, 0))
	while cx <= x_max:
		[y] = step_rk4([f], cx, [y], dx)
		cx += dx
		plot_data_rk4.append((cx, y, y - exact(cx)))
	plot_data_rk4 = np.array(plot_data_rk4)

	plot_data('task3', plot_data_rk1, plot_data_rk2, plot_data_rk4)


def task4():
	t0 = 0.0 # s
	T0 = 550.0 # K
	tt = 60.0 # s
	dt = 1.0 # s
	k = 0.0245 # -
	Tr = 300.0 # K
	f = lambda _t, _T: -k * (_T[0] - Tr)

	# Euler
	ct, cT = t0, T0
	while ct <= tt:
		[cT] = step_rk1([f], ct, [cT], dt)
		ct += dt
	print(f"[RK1] Final temperature: {cT}K")

	# RK2
	ct, cT = t0, T0
	while ct <= tt:
		[cT] = step_rk2([f], ct, [cT], dt)
		ct += dt
	print(f"[RK2] Final temperature: {cT}K")

	# RK4
	ct, cT = t0, T0
	while ct <= tt:
		[cT] = step_rk4([f], ct, [cT], dt)
		ct += dt
	print(f"[RK4] Final temperature: {cT}K")


def task5():
	m0 = 541000.0 # kg
	t0 = 0.0 # s
	s0 = 0.0 # m
	w = 2770.0 # m/s
	tt = 40.0 # s
	dt = 1.0 # s
	df = 1260.0 # kg
	f = lambda _t, _s: w * log(m0/m)

	# Euler
	t, m, s = t0, m0, s0
	while t <= tt:
		[s] = step_rk1([f], t, [s], dt)
		m -= df * dt
		t += dt
	print(f"[RK1] Final height: {s}m")

	# RK2
	t, m, s = t0, m0, s0
	while t <= tt:
		[s] = step_rk2([f], t, [s], dt)
		m -= df * dt
		t += dt
	print(f"[RK2] Final height: {s}m")

	# RK4
	t, m, s = t0, m0, s0
	while t <= tt:
		[s] = step_rk4([f], t, [s], dt)
		m -= df * dt
		t += dt
	print(f"[RK4] Final height: {s}m")


def task7():
	n = 0
	w = 0.5 # omega
	dt = 0.01
	tt = 3
	f1 = lambda x, y: y[0]
	f2 = lambda x, y: y[0] * w ** 2 * x ** 2 - 2 * y[0] * w * (n + 0.5)

	# Euler
	plot_data_rk1 = []
	ct, y1, y2 = 0, 1, 0
	plot_data_rk1.append((ct, y1, y2))
	while ct <= tt:
		y1, y2 = step_rk1([f1, f2], ct, [y1, y2], dt)
		ct += dt
		plot_data_rk1.append((ct, y1, y2))
	plot_data_rk1 = np.array(plot_data_rk1)

	# RK2
	plot_data_rk2 = []
	ct, y1, y2 = 0, 1, 0
	plot_data_rk2.append((ct, y1, y2))
	while ct <= tt:
		y1, y2 = step_rk2([f1, f2], ct, [y1, y2], dt)
		ct += dt
		plot_data_rk2.append((ct, y1, y2))
	plot_data_rk2 = np.array(plot_data_rk2)

	# RK4
	plot_data_rk4 = []
	ct, y1, y2 = 0, 1, 0
	plot_data_rk4.append((ct, y1, y2))
	while ct <= tt:
		y1, y2 = step_rk4([f1, f2], ct, [y1, y2], dt)
		ct += dt
		plot_data_rk4.append((ct, y1, y2))
	plot_data_rk4 = np.array(plot_data_rk4)

	plot_data('shrodinger', plot_data_rk1, plot_data_rk2, plot_data_rk4)


def task8():
	# d^2x/dt^2 + 2b dx/dt + w^2 x = 0
	# dx/dt = v = f1
	# dv/dt = -2b v - w^2 x = f2
	# x(0) = 1
	# v(0) = 0

	# Constant data
	b, w = 1.8, 10  # beta, omega
	dt = 0.01  # delta time
	tt = 5  # total time
	f1 = lambda t, y: y[1]
	f2 = lambda t, y: -2 * b * y[1] - w ** 2 * y[0]

	# Euler
	plot_data_rk1 = []
	ct, x, v = 0, 1, 0
	plot_data_rk1.append((ct, x, v))
	while ct <= tt:
		x, v = step_rk1([f1, f2], ct, [x, v], dt)
		ct += dt
		plot_data_rk1.append((ct, x, v))
	plot_data_rk1 = np.array(plot_data_rk1)

	# RK2
	plot_data_rk2 = []
	ct, x, v = 0, 1, 0
	plot_data_rk2.append((ct, x, v))
	while ct <= tt:
		x, v = step_rk2([f1, f2], ct, [x, v], dt)
		ct += dt
		plot_data_rk2.append((ct, x, v))
	plot_data_rk2 = np.array(plot_data_rk2)

	# RK4
	plot_data_rk4 = []
	ct, x, v = 0, 1, 0
	plot_data_rk4.append((ct, x, v))
	while ct <= tt:
		x, v = step_rk4([f1, f2], ct, [x, v], dt)
		ct += dt
		plot_data_rk4.append((ct, x, v))
	plot_data_rk4 = np.array(plot_data_rk4)

	plot_data('Oscylator tłumiony', plot_data_rk1, plot_data_rk2, plot_data_rk4)


def plot_data(title, data_rk1, data_rk2, data_rk4):
	fig, axs = plt.subplots(2)
	fig.suptitle(title)

	axs[0].plot(data_rk1[:, 0], data_rk1[:, 1], label="RK1", color="red")
	axs[0].plot(data_rk2[:, 0], data_rk2[:, 1], label="RK2", color="green")
	axs[0].plot(data_rk4[:, 0], data_rk4[:, 1], label="RK4", color="blue")
	axs[0].legend()

	axs[1].plot(data_rk1[:, 0], data_rk1[:, 2], label="RK1", color="red")
	axs[1].plot(data_rk2[:, 0], data_rk2[:, 2], label="RK2", color="green")
	axs[1].plot(data_rk4[:, 0], data_rk4[:, 2], label="RK4", color="blue")
	axs[1].legend()

	plt.show()


def step_rk1(f: list[Callable], x: float, y: list[float], h: float) -> list[float]:
	k1 = []
	for i in range(len(y)):
		k1.append(h * f[i](x, y))
	for i in range(len(y)):
		y[i] += k1[i]
	return y


def step_rk2(f: list[Callable], x: float, y: list[float], h: float) -> list[float]:
	k1, k2 = [], []
	for i in range(len(y)):
		k1.append(h * f[i](x, y))
	for i in range(len(y)):
		k2.append(h * f[i](x + h / 2, [z + k1[j] / 2 for j, z in enumerate(y)]))
	for i in range(len(y)):
		y[i] += (k1[i] + k2[i]) / 2
	return y


def step_rk4(f: list[Callable], x: float, y: list[float], h: float) -> list[float]:
	k1, k2, k3, k4 = [], [], [], []
	for i in range(len(y)):
		k1.append(h * f[i](x, y))
	for i in range(len(y)):
		k2.append(h * f[i](x + h / 2, [z + k1[j] / 2 for j, z in enumerate(y)]))
	for i in range(len(y)):
		k3.append(h * f[i](x + h / 2, [z + k2[j] / 2 for j, z in enumerate(y)]))
	for i in range(len(y)):
		k4.append(h * f[i](x + h, [z + k3[j] for j, z in enumerate(y)]))
	for i in range(len(y)):
		y[i] += (k1[i] + 2 * k2[i] + 2 * k3[i] + k4[i]) / 6
	return y


if __name__ == "__main__":
	main()

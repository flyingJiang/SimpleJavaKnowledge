package com.flying.net;

/**
 *
 * https://www.cnblogs.com/bj-mr-li/p/11106390.html
 *
 * TCP连接的TIME_WAIT和CLOSE_WAIT 状态解说
 *    序列号seq：占4个字节，用来标记数据段的顺序，TCP把连接中发送的所有数据字节都编上一个序号，第一个字节的编号由本地随机产生；给字节编上序号后，就给每一个报文段指派一个序号；序列号seq就是这个报文段中的第一个字节的数据编号。
 *     确认号ack：占4个字节，期待收到对方下一个报文段的第一个数据字节的序号；序列号表示报文段携带数据的第一个字节的编号；而确认号指的是期望接收到下一个字节的编号；因此当前报文段最后一个字节的编号+1即为确认号。
 *     确认ACK：占1位，仅当ACK=1时，确认号字段才有效。ACK=0时，确认号无效
 *     同步SYN：连接建立时用于同步序号。当SYN=1，ACK=0时表示：这是一个连接请求报文段。若同意连接，则在响应报文段中使得SYN=1，ACK=1。因此，SYN=1表示这是一个连接请求，或连接接受报文。SYN这个标志位只有在TCP建产连接时才会被置1，握手完成后SYN标志位被置0。
 *     终止FIN：用来释放一个连接。FIN=1表示：此报文段的发送方的数据已经发送完毕，并要求释放运输连接
 *     PS：ACK、SYN和FIN这些大写的单词表示标志位，其值要么是1，要么是0；ack、seq小写的单词表示序号。
 *
 *
 *
 * 三次握手过程理解
 *
 * 第一次握手：建立连接时，客户端发送syn包（syn=j）到服务器，并进入SYN_SENT状态，等待服务器确认；SYN：同步序列编号（Synchronize Sequence Numbers）。
 * 第二次握手：服务器收到syn包，必须确认客户的SYN（ack=j+1），同时自己也发送一个SYN包（syn=k），即SYN+ACK包，此时服务器进入SYN_RECV状态；
 * 第三次握手：客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack=k+1），此包发送完毕，客户端和服务器进入ESTABLISHED（TCP连接成功）状态，完成三次握手。
 * 四次挥手过程理解
 *
 * 1）客户端进程发出连接释放报文，并且停止发送数据。释放数据报文首部，FIN=1，其序列号为seq=u（等于前面已经传送过来的数据的最后一个字节的序号加1），此时，客户端进入FIN-WAIT-1（终止等待1）状态。 TCP规定，FIN报文段即使不携带数据，也要消耗一个序号。
 * 2）服务器收到连接释放报文，发出确认报文，ACK=1，ack=u+1，并且带上自己的序列号seq=v，此时，服务端就进入了CLOSE-WAIT（关闭等待）状态。TCP服务器通知高层的应用进程，客户端向服务器的方向就释放了，这时候处于半关闭状态，即客户端已经没有数据要发送了，但是服务器若发送数据，客户端依然要接受。这个状态还要持续一段时间，也就是整个CLOSE-WAIT状态持续的时间。
 * 3）客户端收到服务器的确认请求后，此时，客户端就进入FIN-WAIT-2（终止等待2）状态，等待服务器发送连接释放报文（在这之前还需要接受服务器发送的最后的数据）。
 * 4）服务器将最后的数据发送完毕后，就向客户端发送连接释放报文，FIN=1，ack=u+1，由于在半关闭状态，服务器很可能又发送了一些数据，假定此时的序列号为seq=w，此时，服务器就进入了LAST-ACK（最后确认）状态，等待客户端的确认。
 * 5）客户端收到服务器的连接释放报文后，必须发出确认，ACK=1，ack=w+1，而自己的序列号是seq=u+1，此时，客户端就进入了TIME-WAIT（时间等待）状态。注意此时TCP连接还没有释放，必须经过2∗∗MSL（最长报文段寿命）的时间后，当客户端撤销相应的TCB后，才进入CLOSED状态。
 * 6）服务器只要收到了客户端发出的确认，立即进入CLOSED状态。同样，撤销TCB后，就结束了这次的TCP连接。可以看到，服务器结束TCP连接的时间要比客户端早一些。
 *  常见面试题
 * 【问题1】为什么连接的时候是三次握手，关闭的时候却是四次握手？
 * 答：因为当Server端收到Client端的SYN连接请求报文后，可以直接发送SYN+ACK报文。其中ACK报文是用来应答的，SYN报文是用来同步的。但是关闭连接时，当Server端收到FIN报文时，很可能并不会立即关闭SOCKET，所以只能先回复一个ACK报文，告诉Client端，"你发的FIN报文我收到了"。只有等到我Server端所有的报文都发送完了，我才能发送FIN报文，因此不能一起发送。故需要四步握手。
 * 【问题2】为什么TIME_WAIT状态需要经过2MSL(最大报文段生存时间)才能返回到CLOSE状态？
 * 答：虽然按道理，四个报文都发送完毕，我们可以直接进入CLOSE状态了，但是我们必须假象网络是不可靠的，有可以最后一个ACK丢失。所以TIME_WAIT状态就是用来重发可能丢失的ACK报文。在Client发送出最后的ACK回复，但该ACK可能丢失。Server如果没有收到ACK，将不断重复发送FIN片段。所以Client不能立即关闭，它必须确认Server接收到了该ACK。Client会在发送出ACK之后进入到TIME_WAIT状态。Client会设置一个计时器，等待2MSL的时间。如果在该时间内再次收到FIN，那么Client会重发ACK并再次等待2MSL。所谓的2MSL是两倍的MSL(Maximum Segment Lifetime)。MSL指一个片段在网络中最大的存活时间，2MSL就是一个发送和一个回复所需的最大时间。如果直到2MSL，Client都没有再次收到FIN，那么Client推断ACK已经被成功接收，则结束TCP连接。
 * 【问题3】为什么不能用两次握手进行连接？
 * 答：3次握手完成两个重要的功能，既要双方做好发送数据的准备工作(双方都知道彼此已准备好)，也要允许双方就初始序列号进行协商，这个序列号在握手过程中被发送和确认。
 *        现在把三次握手改成仅需要两次握手，死锁是可能发生的。作为例子，考虑计算机S和C之间的通信，假定C给S发送一个连接请求分组，S收到了这个分组，并发 送了确认应答分组。按照两次握手的协定，S认为连接已经成功地建立了，可以开始发送数据分组。可是，C在S的应答分组在传输中被丢失的情况下，将不知道S 是否已准备好，不知道S建立什么样的序列号，C甚至怀疑S是否收到自己的连接请求分组。在这种情况下，C认为连接还未建立成功，将忽略S发来的任何数据分 组，只等待连接确认应答分组。而S在发出的分组超时后，重复发送同样的分组。这样就形成了死锁。
 * 【问题4】如果已经建立了连接，但是客户端突然出现故障了怎么办？
 * TCP还设有一个保活计时器，显然，客户端如果出现故障，服务器不能一直等下去，白白浪费资源。服务器每收到一次客户端的请求后都会重新复位这个计时器，时间通常是设置为2小时，若两小时还没有收到客户端的任何数据，服务器就会发送一个探测报文段，以后每隔75秒钟发送一次。若一连发送10个探测报文仍然没反应，服务器就认为客户端出了故障，接着就关闭连接。
 * ---------------------
 * TIME_WAIT调优，则必须理解的几个调优参数:
 * net.ipv4.tcp_timestamps
 * RFC 1323 在 TCP Reliability一节里，引入了timestamp的TCP option，两个4字节的时间戳字段，其中第一个4字节字段用来保存发送该数据包的时间，第二个4字节字段用来保存最近一次接收对方发送到数据的时间。有了这两个时间字段，也就有了后续优化的余地。tcp_tw_reuse 和 tcp_tw_recycle就依赖这些时间字段。
 * net.ipv4.tcp_tw_reuse
 * 从字面意思来看，这个参数是reuse TIME_WAIT状态的连接。时刻记住一条socket连接，就是那个五元组，出现TIME_WAIT状态的连接，一定出现在主动关闭连接的一方。所以，当主动关闭连接的一方，再次向对方发起连接请求的时候（例如，客户端关闭连接，客户端再次连接服务端，此时可以复用了；负载均衡服务器，主动关闭后端的连接，当有新的HTTP请求，负载均衡服务器再次连接后端服务器，此时也可以复用），可以复用TIME_WAIT状态的连接。
 * 通过字面解释以及例子说明，可以看到，tcp_tw_reuse应用的场景：某一方，需要不断的通过“短连接“连接其他服务器，总是自己先关闭连接(TIME_WAIT在自己这方)，关闭后又不断的重新连接对方。
 * 那么，当连接被复用了之后，延迟或者重发的数据包到达，新的连接怎么判断，到达的数据是属于复用后的连接，还是复用前的连接呢？那就需要依赖前面提到的两个时间字段了。复用连接后，这条连接的时间被更新为当前的时间，当延迟的数据达到，延迟数据的时间是小于新连接的时间，所以，内核可以通过时间判断出，延迟的数据可以安全的丢弃掉了。
 * 这个配置，依赖于连接双方，同时对timestamps的支持。同时，这个配置，仅仅影响outbound连接，即做为客户端的角色，连接服务端[connect(dest_ip, dest_port)]时复用TIME_WAIT的socket。
 * net.ipv4.tcp_tw_recycle
 * 从字面意思来看，这个参数是销毁掉 TIME_WAIT。当开启了这个配置后，内核会快速的回收处于TIME_WAIT状态的socket连接。多快？不再是2MSL，而是一个RTO（retransmission timeout，数据包重传的timeout时间）的时间，这个时间根据RTT动态计算出来，但是远小于2MSL。
 * 有了这个配置，还是需要保障丢失重传或者延迟的数据包，不会被新的连接(注意，这里不再是复用了，而是之前处于TIME_WAIT状态的连接已经被destroy掉了，新的连接，刚好是和某一个被destroy掉的连接使用了相同的五元组而已)所错误的接收。在启用该配置，当一个socket连接进入TIME_WAIT状态后，内核里会记录包括该socket连接对应的五元组中的对方IP等在内的一些统计数据，当然也包括从该对方IP所接收到的最近的一次数据包时间。当有新的数据包到达，只要时间晚于内核记录的这个时间，数据包都会被统统的丢掉。
 * 这个配置，依赖于连接双方对timestamps的支持。同时，这个配置，主要影响到了inbound的连接（对outbound的连接也有影响，但是不是复用），即做为服务端角色，客户端连进来，服务端主动关闭了连接，TIME_WAIT状态的socket处于服务端，服务端快速的回收该状态的连接。
 * 由此，如果客户端处于NAT的网络(多个客户端，同一个IP出口的网络环境)，如果配置了tw_recycle，就可能在一个RTO的时间内，只能有一个客户端和自己连接成功(不同的客户端发包的时间不一致，造成服务端直接把数据包丢弃掉)。
 * 下面通过案例和图示，来加深下理解:
 *
 * -  客户端IP地址为：180.172.35.150，我们可以认为是浏览器;
 * -  负载均衡有两个IP，外网IP地址为 115.29.253.156，内网地址为10.162.74.10；外网地址监听80端口;
 * -  负载均衡背后有两台Web服务器，一台IP地址为 10.162.74.43，监听80端口；另一台为 10.162.74.44，监听 80 端口;
 * -  Web服务器会连接数据服务器，IP地址为 10.162.74.45，监听 3306 端口;
 * 这种简单的架构下，我们来看看，在不同的情况下，上面谈论的tw_reuse/tw_recycle对网络连接的影响。
 * 先做个假定：
 * -  客户端通过HTTP/1.1连接负载均衡，也就是说，HTTP协议投Connection为keep-alive，所以假定，客户端对负载均衡服务器的socket连接，客户端会断开连接，所以TIME_WAIT出现在客户端;
 * -  Web服务器和MySQL服务器的连接，我们假定，Web服务器上的程序在连接结束的时候，调用close操作关闭socket资源连接，所以，TIME_WAIT出现在 Web 服务器端。
 * 那么，在这种假定下：
 * -  Web服务器上，肯定可以配置开启的配置：tcp_tw_reuse；如果Web服务器有很多连向DB服务器的连接，可以保证socket连接的复用。
 * -  那么，负载均衡服务器和Web服务器，谁先关闭连接，则决定了我们怎么配置tcp_tw_reuse/tcp_tw_recycle了。
 * 方案一：负载均衡服务器
 * 首先关闭连接, 在这种情况下，因为负载均衡服务器对Web服务器的连接，TIME_WAIT大都出现在负载均衡服务器上，所以:
 * 在负载均衡服务器上的配置：
 * net.ipv4.tcp_tw_reuse = 1            //尽量复用连接
 * net.ipv4.tcp_tw_recycle = 0         //不能保证客户端不在NAT的网络啊
 * 在Web服务器上的配置为：
 * net.ipv4.tcp_tw_reuse = 1         //这个配置主要影响的是Web服务器到DB服务器的连接复用
 * net.ipv4.tcp_tw_recycle：  设置成1和0都没有任何意义。想一想，在负载均衡和它的连接中，它是服务端，但是TIME_WAIT出现在负载均衡服务器上；它和DB的连接，它是客户端，recycle对它并没有什么影响，关键是reuse
 * 方案二：Web服务器首先关闭来自负载均衡服务器的连接
 * 在这种情况下，Web服务器变成TIME_WAIT的重灾区。负载均衡对Web服务器的连接，由Web服务器首先关闭连接，TIME_WAIT出现在Web服务器上；Web服务器对DB服务器的连接，由Web服务器关闭连接，TIME_WAIT也出现在它身上，此时:
 * 负载均衡服务器上的配置：
 * net.ipv4.tcp_tw_reuse：0 或者 1 都行，都没有实际意义
 * net.ipv4.tcp_tw_recycle=0           //一定是关闭recycle
 * 在Web服务器上的配置：
 * net.ipv4.tcp_tw_reuse = 1       //这个配置主要影响的是Web服务器到DB服务器的连接复用
 * net.ipv4.tcp_tw_recycle=1      //由于在负载均衡和Web服务器之间并没有NAT的网络，可以考虑开启recycle，加速由于负载均衡和Web服务器之间的连接造成的大量TIME_WAIT
 * 问题1: 通常说的连接池可以复用连接，是不是意味着，需要等到上个连接time wait结束后才能再次使用?
 * 所谓连接池复用，复用的一定是活跃的连接，所谓活跃，第一表明连接池里的连接都是ESTABLISHED的，第二，连接池做为上层应用，会有定时的心跳去保持连接的活跃性。既然连接都是活跃的，那就不存在有TIME_WAIT的概念了，在上篇里也有提到，TIME_WAIT是在主动关闭连接的一方，在关闭连接后才进入的状态。既然已经关闭了，那么这条连接肯定已经不在连接池里面了，即被连接池释放了。
 * 问题2: 作为负载均衡的机器随机端口使用完的情况下大量time_wait，不调整上面文中说的那三个参数，有其他的更好的方案吗？
 * 第一，随机端口使用完，你可以通过调整/etc/sysctl.conf下的net.ipv4.ip_local_port_range配置，至少修改成 net.ipv4.ip_local_port_range=1024 65535，保证你的负载均衡服务器至少可以使用6万个随机端口，也即可以有6万的反向代理到后端的连接，可以支持每秒1000的并发（想一想，因为TIME_WAIT状态会持续1分钟后消失，所以一分钟最多有6万，每秒1000）；如果这么多端口都使用完了，也证明你应该加服务器了，或者，你的负载均衡服务器需要配置多个IP地址，或者，你的后端服务器需要监听更多的端口和配置更多的IP（想一下socket的五元组）
 * 第二，大量的TIME_WAIT，多大量？如果是几千个，其实不用担心，因为这个内存和CPU的消耗有一些，但是是可以忽略的。
 * 第三，如果真的量很大，上万上万的那种，可以考虑，让后端的服务器主动关闭连接，如果后端服务器没有外网的连接只有负载均衡服务器的连接（主要是没有NAT网络的连接），可以在后端服务器上配置tw_recycle，然后同时，在负载均衡服务器上，配置tw_reuse。
 */
public class Test {
}
